package com.nttdatabc.mscreditos.service;

import com.nttdatabc.mscreditos.model.Credit;
import com.nttdatabc.mscreditos.repository.CreditRepository;
import com.nttdatabc.mscreditos.service.interfaces.CreditService;
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

import static org.mockito.Mockito.*;

@SpringBootTest
public class CreditServiceTest {
  @Mock
  private CreditRepository creditRepository;
  @InjectMocks
  private CreditServiceImpl creditService;
  @BeforeEach
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }
  @Test
  void getCreditByIdService_ShouldReturnCredit_WhenCreditExists() {
    // Arrange
    String creditId = "credit1";
    Credit credit = new Credit();
    credit.setId(creditId);
    when(creditRepository.findById(creditId))
        .thenReturn(Mono.just(credit));

    // Act
    Mono<Credit> result = creditService.getCreditByIdService(creditId);

    // Assert
    StepVerifier.create(result)
        .expectNext(credit)
        .verifyComplete();

    verify(creditRepository).findById(creditId);
  }

  @Test
  void getCreditByIdService_ShouldReturnError_WhenCreditDoesNotExist() {
    // Arrange
    String creditId = "credit1";

    when(creditRepository.findById(creditId))
        .thenReturn(Mono.empty());

    // Act
    Mono<Credit> result = creditService.getCreditByIdService(creditId);

    // Assert
    StepVerifier.create(result)
        .expectError(ErrorResponseException.class)
        .verify();

    verify(creditRepository).findById(creditId);
  }

  @Test
  void deleteCreditById_ShouldDeleteCredit_WhenCreditExists() {
    // Arrange
    String creditId = "credit1";
    Credit credit = new Credit();
    credit.setId(creditId);

    when(creditRepository.delete(credit))
        .thenReturn(Mono.empty());
    when(creditRepository.findById(creditId))
        .thenReturn(Mono.just(credit));

    // Act
    Mono<Void> result = creditService.deleteCreditById(creditId);

    // Assert
    StepVerifier.create(result)
        .verifyComplete();

    verify(creditRepository).delete(credit);
  }
  @Test
  void deleteCreditById_ShouldReturnError_WhenCreditDoesNotExist() {
    // Arrange
    String creditId = "credit1";

    when(creditRepository.findById(creditId))
        .thenReturn(Mono.empty());

    // Act
    Mono<Void> result = creditService.deleteCreditById(creditId);

    // Assert
    StepVerifier.create(result)
        .expectError(ErrorResponseException.class)
        .verify();

    verify(creditRepository, never()).delete(any());
  }

  @Test
  void getCreditsByCustomerId_ShouldReturnCredits_WhenCreditsExist() {
    // Arrange
    String customerId = "customer1";
    Credit credit1 = new Credit();
    credit1.setId("credit1");
    Credit credit2 = new Credit();
    credit2.setId("credit2");

    List<Credit> creditList = Arrays.asList(credit1, credit2);

    when(creditRepository.findByCustomerId(customerId))
        .thenReturn(Flux.fromIterable(creditList));

    // Act
    Flux<Credit> result = creditService.getCreditsByCustomerId(customerId);

    // Assert
    StepVerifier.create(result)
        .expectNext(credit1)
        .expectNext(credit2)
        .verifyComplete();

    verify(creditRepository).findByCustomerId(customerId);
  }

  @Test
  void getCreditsByCustomerId_ShouldReturnEmptyFlux_WhenNoCreditsExist() {
    // Arrange
    String customerId = "customer1";

    when(creditRepository.findByCustomerId(customerId))
        .thenReturn(Flux.empty());

    // Act
    Flux<Credit> result = creditService.getCreditsByCustomerId(customerId);

    // Assert
    StepVerifier.create(result)
        .verifyComplete();

    verify(creditRepository).findByCustomerId(customerId);
  }
}
