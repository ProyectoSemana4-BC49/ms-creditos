package com.nttdatabc.mscreditos.service;

import static com.nttdatabc.mscreditos.utils.Constantes.*;
import static com.nttdatabc.mscreditos.utils.MovementValidator.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nttdatabc.mscreditos.model.Credit;
import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.enums.StatusCredit;
import com.nttdatabc.mscreditos.model.helpers.HasDebtResponse;
import com.nttdatabc.mscreditos.model.helpers.PaidInstallment;
import com.nttdatabc.mscreditos.repository.MovementRepository;
import com.nttdatabc.mscreditos.service.interfaces.MovementService;
import com.nttdatabc.mscreditos.utils.Utilitarios;
import com.nttdatabc.mscreditos.utils.exceptions.errors.ErrorResponseException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Clase service movements.
 */
@Service
public class MovementServiceImpl implements MovementService {
  @Autowired
  private MovementRepository movementRepository;
  @Autowired
  private CreditServiceImpl creditServiceImpl;
  @Autowired
  private KafkaTemplate kafkaTemplate;

  @Override
  public Mono<Void> createMovementCreditService(MovementCredit movementCredit) {
    return validateMovementNoNulls(movementCredit)
        .then(validateMovementEmpty(movementCredit))
        .then(verifyValues(movementCredit))
        .then(validateCreditRegister(movementCredit.getCreditId(), creditServiceImpl))
        .then(creditServiceImpl.getCreditByIdService(movementCredit.getCreditId()))
        .flatMap(infoCreditById -> {
          if (infoCreditById.getMountLimit().doubleValue() < movementCredit.getAmount().doubleValue()) {
            return Mono.error(new ErrorResponseException(EX_ERROR_AMOUNT_CREDIT,
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          }
          if (movementCredit.getTotalInstallments() > MAX_SIZE_INSTALLMENTS) {
            return Mono.error(new ErrorResponseException(EX_ERROR_INSTALLMENTS,
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          }

          movementCredit.setId(Utilitarios.generateUuid());
          movementCredit.setDayCreated(LocalDateTime.now().toString());
          movementCredit.setStatus(StatusCredit.ACTIVO.toString());
          movementCredit.setPaidInstallments(new ArrayList<>());

          return movementRepository.save(movementCredit)
              .flatMap(savedMovementCredit -> {
                BigDecimal newLimit = infoCreditById.getMountLimit().subtract(movementCredit.getAmount());
                infoCreditById.setMountLimit(newLimit);
                return creditServiceImpl.updateCreditService(infoCreditById);
              });
        })
        .then();

  }

  @Override
  public Flux<MovementCredit> getMovementsCreditsByCreditIdService(String creditId) {
    return validateCreditRegister(creditId, creditServiceImpl)
        .thenMany(movementRepository.findByCreditId(creditId));
  }

  @Override
  public Mono<MovementCredit> getMovementCreditByIdService(String movementId) {
    return movementRepository.findById(movementId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<Void> createPaymentInstallmentByMovementId(String movementId, PaidInstallment paidInstallment) {
    return paymentIsValid(paidInstallment)
        .then(getMovementCreditByIdService(movementId))
        .flatMap(movementCredit -> {
          if (movementCredit.getTotalInstallments().intValue() <= movementCredit.getPaidInstallments().size()) {
            return Mono.error(new ErrorResponseException(EX_ERROR_PAYMENT_LIMIT,
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          }

          return validateCreditRegister(movementCredit.getCreditId(), creditServiceImpl)
              .then(Mono.just(paidInstallment))
              .map(paidInstallmentFlujo -> {
                paidInstallmentFlujo.setId(Utilitarios.generateUuid());
                paidInstallmentFlujo.setDatePayment(LocalDateTime.now().toString());
                paidInstallmentFlujo.setInstallmentNumber(movementCredit.getPaidInstallments().size() + 1);
                movementCredit.getPaidInstallments().add(paidInstallmentFlujo);
                return movementCredit;
              })
              .flatMap(this::updateMovementCreditService)
              .then(Mono.just(movementId))
              .then(getMovementCreditByIdService(movementId))
              .flatMap(movementCreditVerify -> {
                if (movementCreditVerify.getTotalInstallments().intValue() == movementCredit.getPaidInstallments().size()) {
                  movementCreditVerify.setStatus(StatusCredit.PAGADO.toString());
                  return updateMovementCreditService(movementCreditVerify);
                } else {
                  return Mono.empty();
                }
              });
        })
        .then();
  }

  @Override
  public Mono<Void> updateMovementCreditService(MovementCredit movementCredit) {
    return getMovementCreditByIdService(movementCredit.getId())
        .map(movementCreditFound -> {
          movementCreditFound.setAmount(movementCredit.getAmount());
          movementCreditFound.setStatus(movementCredit.getStatus());
          movementCreditFound.setDueDate(movementCredit.getDueDate());
          movementCreditFound.setPaidInstallments(movementCredit.getPaidInstallments());
          movementCreditFound.setTotalInstallments(movementCredit.getTotalInstallments());
          return movementCreditFound;
        }).flatMap(movementCreditUpdate -> movementRepository.save(movementCreditUpdate))
        .then();
  }

  @Override
  public Mono<Void> deleteMovementCredit(String movementId) {
    return getMovementCreditByIdService(movementId)
        .flatMap(movementCredit -> movementRepository.delete(movementCredit))
        .then();
  }

  @Override
  public Mono<HasDebtResponse> hasDebtCreditCustomerService(String customerId) {
    return
        creditServiceImpl.getCreditsByCustomerId(customerId)
            .switchIfEmpty(Mono.just(new Credit()))
            .flatMap(creditCustomer -> {
              if (creditCustomer.getId() == null) {
                return Mono.just(new HasDebtResponse(false));
              }
              return getMovementsCreditsByCreditIdService(creditCustomer.getId())
                  .filter(movementCredit -> movementCredit.getStatus().equals("ACTIVO"))
                  .any(movementCredit -> {
                    Integer dueDate = Integer.parseInt(movementCredit.getDueDate());
                    List<PaidInstallment> paidMents = movementCredit.getPaidInstallments();
                    if (paidMents.isEmpty()) {
                      return false;
                    }
                    Boolean isAfterPaid = paidMents.stream().anyMatch(paidInstallment -> {
                      LocalDateTime dayPayment = LocalDateTime.parse(paidInstallment.getDatePayment());
                      return dayPayment.getDayOfMonth() > dueDate;
                    });
                    return isAfterPaid;

                  }).flatMap(aBoolean -> Mono.just(new HasDebtResponse(aBoolean)));
            }).flatMap(Mono::just)
            .single();
  }

  @KafkaListener(topics = {"has-debt-credit"}, groupId = "my-group-id")
  public void listenerRequestHasDebt(String message) {
    Gson gson = new Gson();
    Map<String, String> map = gson.fromJson(message, new TypeToken<Map<String, String>>() {
    }.getType());
    String customerId = map.get("customerId");
    Mono<HasDebtResponse> hasDebtResponseMono = hasDebtCreditCustomerService(customerId);
    hasDebtResponseMono.subscribe(hasDebtResponse -> {
      String jsonDataMono = gson.toJson(hasDebtResponse);
      kafkaTemplate.send("response-has-debt-credit", jsonDataMono);
    });
  }
}
