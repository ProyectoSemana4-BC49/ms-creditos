package com.nttdatabc.mscreditos.controller;

import static com.nttdatabc.mscreditos.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.mscreditos.controller.interfaces.CreditControllerApi;
import com.nttdatabc.mscreditos.model.Credit;
import com.nttdatabc.mscreditos.service.CreditServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



/**
 * Controller Credit.
 */
@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class CreditController implements CreditControllerApi {
  @Autowired
  private CreditServiceImpl creditServiceImpl;

  @Override
  public ResponseEntity<Mono<Void>> createCredit(Credit credit, ServerWebExchange exchange) {
    return new ResponseEntity<>(creditServiceImpl.createCreditService(credit)
        .doOnSubscribe(unused -> log.info("getAllCredits:: iniciando"))
        .doOnError(throwable -> log.error("getAllCredits:: error " + throwable.getMessage()))
        .doOnSuccess((s) -> log.info("getAllCredits:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> deleteCreditById(String creditId, ServerWebExchange exchange) {
    return new ResponseEntity<>(creditServiceImpl.deleteCreditById(creditId)
        .doOnSubscribe(unused -> log.info("deleteCreditById:: iniciando"))
        .doOnError(throwable -> log.error("deleteCreditById:: error " + throwable.getMessage()))
        .doOnSuccess((s) -> log.info("deleteCreditById:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<Credit>> getAllCredits(ServerWebExchange exchange) {
    return new ResponseEntity<>(creditServiceImpl.getAllCreditsService()
        .doOnSubscribe(unused -> log.info("getAllCredits:: iniciando"))
        .doOnError(throwable -> log.error("getAllCredits:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getAllCredits:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Credit>> getCreditById(String creditId, ServerWebExchange exchange) {
    return new ResponseEntity<>(creditServiceImpl.getCreditByIdService(creditId)
        .doOnSubscribe(unused -> log.info("getCreditById:: iniciando"))
        .doOnError(throwable -> log.error("getCreditById:: error " + throwable.getMessage()))
        .doOnSuccess((e) -> log.info("getCreditById:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<Credit>> getCreditsByCustomerId(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(creditServiceImpl.getCreditsByCustomerId(customerId)
        .doOnSubscribe(unused -> log.info("getCreditsByCustomerId:: iniciando"))
        .doOnError(throwable -> log.error("getCreditsByCustomerId:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getCreditsByCustomerId:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateCredit(Credit credit, ServerWebExchange exchange) {
    return CreditControllerApi.super.updateCredit(credit, exchange);
  }
}
