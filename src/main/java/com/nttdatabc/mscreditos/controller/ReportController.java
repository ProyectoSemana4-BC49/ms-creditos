package com.nttdatabc.mscreditos.controller;


import static com.nttdatabc.mscreditos.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.mscreditos.controller.interfaces.ReportControllerApi;
import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.helpers.BalanceAccounts;
import com.nttdatabc.mscreditos.service.ReportServiceImpl;
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
 * Report controller.
 */
@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class ReportController implements ReportControllerApi {
  @Autowired
  private ReportServiceImpl reportServiceImpl;

  @Override
  public ResponseEntity<Mono<BalanceAccounts>> getBalanceCredit(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportServiceImpl.getBalanceAverageService(customerId)
        .doOnSubscribe(unused -> log.info("getBalanceCredit:: iniciando"))
        .doOnError(throwable -> log.error("getBalanceCredit:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("getBalanceCredit:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<MovementCredit>> getLastMovementsCardCredit(String creditId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportServiceImpl.getLastMovementsCardCreditService(creditId)
        .doOnSubscribe(unused -> log.info("getLastMovementsCardCredit:: iniciando"))
        .doOnError(throwable -> log.error("getLastMovementsCardCredit:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getLastMovementsCardCredit:: finalizado con exito"))
        , HttpStatus.OK);
  }
}
