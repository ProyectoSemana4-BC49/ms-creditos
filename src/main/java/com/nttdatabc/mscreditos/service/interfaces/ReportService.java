package com.nttdatabc.mscreditos.service.interfaces;

import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.helpers.BalanceAccounts;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Report service interface.
 */
public interface ReportService {
  Mono<BalanceAccounts> getBalanceAverageService(String customerId);

  Flux<MovementCredit> getLastMovementsCardCreditService(String creditId);
}

