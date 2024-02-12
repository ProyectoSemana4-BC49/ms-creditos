package com.nttdatabc.mscreditos.service.interfaces;

import com.nttdatabc.mscreditos.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reposirotory credit.
 */
public interface CreditService {
  Flux<Credit> getAllCreditsService();

  Mono<Credit> getCreditByIdService(String creditId);

  Mono<Void> createCreditService(Credit credit);

  Mono<Void> updateCreditService(Credit credit);

  Mono<Void> deleteCreditById(String creditId);

  Flux<Credit> getCreditsByCustomerId(String customerId);
}

