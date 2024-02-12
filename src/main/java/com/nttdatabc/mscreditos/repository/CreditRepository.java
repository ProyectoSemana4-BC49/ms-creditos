package com.nttdatabc.mscreditos.repository;

import com.nttdatabc.mscreditos.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository credit.
 */
@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {

  Flux<Credit> findByCustomerId(String customerId);
}

