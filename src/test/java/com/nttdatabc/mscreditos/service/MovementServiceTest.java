package com.nttdatabc.mscreditos.service;

import com.nttdatabc.mscreditos.model.Credit;
import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.repository.MovementRepository;
import com.nttdatabc.mscreditos.utils.MovementValidator;
import com.nttdatabc.mscreditos.utils.exceptions.errors.ErrorResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static com.nttdatabc.mscreditos.utils.MovementValidator.validateCreditRegister;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovementServiceTest {
  @Mock
  private CreditServiceImpl creditServiceImpl;
  @Mock
  private MovementRepository movementRepository;
  @Mock
  private CreditServiceImpl creditService;
  @Mock
  private MovementValidator movementValidator;

  @InjectMocks
  private MovementServiceImpl movementService;
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void getMovementCreditByIdService_ShouldReturnMovement_WhenMovementExists() {
    // Arrange
    String movementId = "movement1";
    MovementCredit movement = new MovementCredit();
    movement.setId(movementId);
    when(movementRepository.findById(movementId))
        .thenReturn(Mono.just(movement));

    // Act
    Mono<MovementCredit> result = movementService.getMovementCreditByIdService(movementId);

    // Assert
    StepVerifier.create(result)
        .expectNext(movement)
        .verifyComplete();

    verify(movementRepository).findById(movementId);
  }
  @Test
  void getMovementCreditByIdService_ShouldReturnError_WhenMovementDoesNotExist() {
    // Arrange
    String movementId = "movement1";
    when(movementRepository.findById(movementId))
        .thenReturn(Mono.empty());

    // Act
    Mono<MovementCredit> result = movementService.getMovementCreditByIdService(movementId);

    // Assert
    StepVerifier.create(result)
        .expectError(ErrorResponseException.class)
        .verify();

    verify(movementRepository).findById(movementId);
  }

  @Test
  void deleteMovementCredit_Success() {
    // Arrange
    String movementId = "movement1";
    MovementCredit movement = new MovementCredit();
    movement.setId(movementId);

    when(movementRepository.findById(movementId))
        .thenReturn(Mono.just(movement));
    when(movementRepository.delete(movement))
        .thenReturn(Mono.empty());

    // Act
    Mono<Void> result = movementService.deleteMovementCredit(movementId);

    // Assert
    StepVerifier.create(result)
        .verifyComplete();


    verify(movementRepository).delete(movement);
  }
  @Test
  void deleteMovementCredit_Error() {
    // Arrange
    String movementId = "movement1";
    MovementCredit movement = new MovementCredit();
    movement.setId(movementId);

    when(movementRepository.findById(movementId))
        .thenReturn(Mono.empty());
    when(movementRepository.delete(movement))
        .thenReturn(Mono.empty());

    // Act
    Mono<Void> result = movementService.deleteMovementCredit(movementId);

    // Assert
    StepVerifier.create(result)
        .expectError(ErrorResponseException.class);

  }
}
