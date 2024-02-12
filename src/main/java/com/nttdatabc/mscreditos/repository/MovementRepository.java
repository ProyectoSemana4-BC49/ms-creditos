package com.nttdatabc.mscreditos.repository;

import com.nttdatabc.mscreditos.model.MovementCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Movement repository.
 */
@Repository
public interface MovementRepository extends ReactiveMongoRepository<MovementCredit, String> {
  Flux<MovementCredit> findByCreditId(String creditId);
  Flux<MovementCredit> findTop10ByOrderByDayCreatedDesc(String creditId);
}

