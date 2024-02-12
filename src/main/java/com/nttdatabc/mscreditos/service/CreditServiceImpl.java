package com.nttdatabc.mscreditos.service;

import static com.nttdatabc.mscreditos.utils.Constantes.*;
import static com.nttdatabc.mscreditos.utils.CreditValidator.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nttdatabc.mscreditos.config.kafka.KafkaConsumerListener;
import com.nttdatabc.mscreditos.model.Credit;
import com.nttdatabc.mscreditos.model.enums.TypeCredit;
import com.nttdatabc.mscreditos.model.enums.TypeCustomer;
import com.nttdatabc.mscreditos.repository.CreditRepository;
import com.nttdatabc.mscreditos.service.interfaces.CreditService;
import com.nttdatabc.mscreditos.utils.CreditValidator;
import com.nttdatabc.mscreditos.utils.Utilitarios;
import com.nttdatabc.mscreditos.utils.exceptions.errors.ErrorResponseException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service de credito.
 */
@Service
public class CreditServiceImpl implements CreditService {

  @Autowired
  private CreditRepository creditRepository;

  @Autowired
  private KafkaTemplate kafkaTemplate;
  @Autowired
  private CreditValidator creditValidator;
  @Autowired
  private KafkaConsumerListener kafkaConsumerListener;
  @Autowired
  @Qualifier("reactiveRedisTemplateCredit")
  private ReactiveRedisTemplate<String, Credit> redisTemplate;

  @Override
  public Flux<Credit> getAllCreditsService() {
    String cacheKey = "credit";
    Duration cacheDuration = Duration.ofSeconds(50);
    return redisTemplate.opsForList().range(cacheKey, 0, -1)
        .switchIfEmpty(
            creditRepository.findAll()
                .flatMap(debitCard -> redisTemplate.opsForList().leftPushAll(cacheKey, debitCard)
                    .thenMany(Flux.just(debitCard))))
        .cache(cacheDuration)
        .doOnSubscribe(subscription -> redisTemplate.expire(cacheKey, cacheDuration).subscribe());
  }


  @Override
  public Mono<Credit> getCreditByIdService(String creditId) {
    return creditRepository.findById(creditId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<Void> createCreditService(Credit credit) {
    return validateCreditsNoNulls(credit)
        .then(validateCreditsEmpty(credit))
        .then(verifyTypeCredits(credit))
        .then(verifyValues(credit))
        .then(Mono.just(credit))
        .flatMap(creditFlux -> creditValidator.verifyCustomerExists(creditFlux.getCustomerId(), kafkaConsumerListener)
            .flatMap(customerFound -> creditRepository.findByCustomerId(customerFound.get_id())
                .collectList()
                .flatMap(listCreditsByCustomer -> {
                  if (customerFound.getType().equalsIgnoreCase(TypeCustomer.PERSONA.toString())
                      && listCreditsByCustomer.size() >= MAX_SIZE_ACCOUNT_CUSTOMER_PERSONA) {
                    return Mono.error(new ErrorResponseException(EX_ERROR_CONFLICTO_CUSTOMER_PERSONA,
                        HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
                  }
                  return Mono.just(customerFound);
                })))
        .then(Mono.just(credit))
        .map(creditFlujo -> {
          creditFlujo.setId(Utilitarios.generateUuid());
          creditFlujo.setDateOpen(LocalDateTime.now().toString());
          creditFlujo.setInterestRate(BigDecimal.valueOf(INTEREST_RATE));
          return creditFlujo;
        }).flatMap(creditInsert -> creditRepository.save(creditInsert))
        .then();
  }

  @Override
  public Mono<Void> updateCreditService(Credit credit) {
    return validateCreditsNoNulls(credit)
        .then(validateCreditsEmpty(credit))
        .then(verifyTypeCredits(credit))
        .then(getCreditByIdService(credit.getId()))
        .map(creditFlujo -> {
          creditFlujo.setMountLimit(credit.getMountLimit());
          creditFlujo.setTypeCredit(credit.getTypeCredit());
          return creditFlujo;
        }).flatMap(creditProcesado -> creditRepository.save(creditProcesado))
        .then();
  }

  @Override
  public Mono<Void> deleteCreditById(String creditId) {
    return getCreditByIdService(creditId)
        .flatMap(credit -> creditRepository.delete(credit))
        .then();
  }

  @Override
  public Flux<Credit> getCreditsByCustomerId(String customerId) {
    return creditRepository.findByCustomerId(customerId);
  }


  @KafkaListener(topics = {"get-credits-bycustomer"}, groupId = "my-group-id")
  public void listenerGetCreditsByCustomer(String message) {
    Gson gson = new Gson();
    Map<String, String> map = gson.fromJson(message, new TypeToken<Map<String, String>>() {
    }.getType());
    String customerId = map.get("customerId");
    Flux<Credit> getCredits = creditRepository.findByCustomerId(customerId);
    getCredits.collectList().subscribe(creditList -> {
      String jsonData = gson.toJson(creditList);
      kafkaTemplate.send("response-get-credits-bycustomer", jsonData);
    });
  }

  @KafkaListener(topics = {"has-card-debt"}, groupId = "my-group-id")
  public void listenerGetHasCardDebt(String message) {
    Gson gson = new Gson();
    Map<String, String> map = gson.fromJson(message, new TypeToken<Map<String, String>>() {
    }.getType());
    String customerId = map.get("customerId");
    Flux<Credit> creditFlux = creditRepository.findByCustomerId(customerId);
    creditFlux
        .any(credit -> credit.getTypeCredit().equalsIgnoreCase(TypeCredit.TARJETA.toString()))
        .subscribe(aBoolean -> {
          Map<String, String> responseBoolean = new HashMap<>();
          responseBoolean.put("has_credit_customer", aBoolean.toString());
          kafkaTemplate.send("response-has-card-debt", gson.toJson(responseBoolean));
        });

  }
}
