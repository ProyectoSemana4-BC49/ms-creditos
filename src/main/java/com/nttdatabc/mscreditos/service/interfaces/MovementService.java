package com.nttdatabc.mscreditos.service.interfaces;

import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.helpers.HasDebtResponse;
import com.nttdatabc.mscreditos.model.helpers.PaidInstallment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository movement.
 */
public interface MovementService {
  Mono<Void> createMovementCreditService(MovementCredit movementCredit);

  Flux<MovementCredit> getMovementsCreditsByCreditIdService(String creditId);

  Mono<MovementCredit> getMovementCreditByIdService(String movementId);

  Mono<Void> createPaymentInstallmentByMovementId(String movementId, PaidInstallment paidInstallment);

  Mono<Void> updateMovementCreditService(MovementCredit movementCredit);

  Mono<Void> deleteMovementCredit(String movementId);

  Mono<HasDebtResponse> hasDebtCreditCustomerService(String customerId);
}

