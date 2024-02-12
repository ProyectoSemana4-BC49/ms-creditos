package com.nttdatabc.mscreditos.controller;

import static com.nttdatabc.mscreditos.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.mscreditos.controller.interfaces.MovementControllerApi;
import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.helpers.HasDebtResponse;
import com.nttdatabc.mscreditos.model.helpers.PaidInstallment;
import com.nttdatabc.mscreditos.service.MovementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Controller de movements.
 */
@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class MovementController implements MovementControllerApi {
  @Autowired
  private MovementServiceImpl movementServiceImpl;

  @Override
  public ResponseEntity<Mono<Void>> createMovementCredit(MovementCredit movementCredit, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementServiceImpl.createMovementCreditService(movementCredit)
        .doOnSubscribe(unused -> log.info("createMovementCredit:: iniciando"))
        .doOnError(throwable -> log.error("createMovementCredit:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createMovementCredit:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> createPaymentInstallmentByMovementId(String movementId, PaidInstallment paidInstallment, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementServiceImpl.createPaymentInstallmentByMovementId(movementId, paidInstallment)
        .doOnSubscribe(unused -> log.info("createPaymentInstallmentByMovementId:: iniciando"))
        .doOnError(throwable -> log.error("createPaymentInstallmentByMovementId:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createPaymentInstallmentByMovementId:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> deleteMovementCredit(String movementId, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementServiceImpl.deleteMovementCredit(movementId)
        .doOnSubscribe(unused -> log.info("deleteMovementCredit:: iniciando"))
        .doOnError(throwable -> log.error("deleteMovementCredit:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("deleteMovementCredit:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<MovementCredit>> getMovementCreditById(String movementId, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementServiceImpl.getMovementCreditByIdService(movementId)
        .doOnSubscribe(unused -> log.info("getMovementCreditById:: iniciando"))
        .doOnError(throwable -> log.error("getMovementCreditById:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("getMovementCreditById:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<MovementCredit>> getMovementsCreditsByCreditId(String creditId, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementServiceImpl.getMovementsCreditsByCreditIdService(creditId)
        .doOnSubscribe(unused -> log.info("getMovementsCreditsByCreditId:: iniciando"))
        .doOnError(throwable -> log.error("getMovementsCreditsByCreditId:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getMovementsCreditsByCreditId:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Void>> updateMovementCredit(MovementCredit movementCredit, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementServiceImpl.updateMovementCreditService(movementCredit)
        .doOnSubscribe(unused -> log.info("updateMovementCredit:: iniciando"))
        .doOnError(throwable -> log.error("updateMovementCredit:: error " + throwable.getMessage()))
        .doOnSuccess((e) -> log.info("updateMovementCredit:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @GetMapping("/movement_credits/has_debt/{customerId}")
  public ResponseEntity<Mono<HasDebtResponse>> hasDebtCreditCustomer(@PathVariable String customerId) {
    return new ResponseEntity<>(movementServiceImpl.hasDebtCreditCustomerService(customerId)
        .doOnSubscribe(unused -> log.info("hasDebtCreditCustomer:: iniciando"))
        .doOnError(throwable -> log.error("hasDebtCreditCustomer:: error " + throwable.getMessage()))
        .doOnSuccess((e) -> log.info("hasDebtCreditCustomer:: finalizado con exito"))
        , HttpStatus.OK);
  }
}
