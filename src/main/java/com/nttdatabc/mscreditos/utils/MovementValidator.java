package com.nttdatabc.mscreditos.utils;

import static com.nttdatabc.mscreditos.utils.Constantes.*;

import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.helpers.PaidInstallment;
import com.nttdatabc.mscreditos.service.CreditServiceImpl;
import com.nttdatabc.mscreditos.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * Clase movement validator.
 */
public class MovementValidator {
  /**
   * Valida que los campos obligatorios de un movimiento de crédito no sean nulos.
   *
   * @param movement El movimiento de crédito a validar.
   * @throws ErrorResponseException Si algún campo obligatorio es nulo.
   */
  public static Mono<Void> validateMovementNoNulls(MovementCredit movement) {
    return Mono.just(movement)
        .filter(m -> m.getCreditId() != null)
        .filter(m -> m.getTotalInstallments() != null)
        .filter(m -> m.getAmount() != null)
        .filter(m -> m.getDueDate() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Valida que los campos obligatorios de un movimiento de crédito no estén vacíos.
   *
   * @param movement El movimiento de crédito a validar.
   * @throws ErrorResponseException Si algún campo obligatorio está vacío.
   */
  public static Mono<Void> validateMovementEmpty(MovementCredit movement) {
    return Mono.just(movement)
        .filter(m -> !m.getCreditId().isBlank())
        .filter(m -> !m.getTotalInstallments().toString().isBlank())
        .filter(m -> !m.getAmount().toString().isBlank())
        .filter(m -> !m.getDueDate().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Verifica si un crédito con el ID proporcionado está registrado.
   *
   * @param creditId          El ID del crédito a verificar.
   * @param creditServiceImpl El servicio para obtener información del crédito.
   * @throws ErrorResponseException Si el crédito no está registrado.
   */
  public static Mono<Void> validateCreditRegister(String creditId, CreditServiceImpl creditServiceImpl) {
    return creditServiceImpl.getCreditByIdService(creditId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)))
        .then();
  }

  /**
   * Verifica que los valores de un movimiento de crédito sean válidos.
   *
   * @param movement El movimiento de crédito a verificar.
   * @throws ErrorResponseException Si algún valor no es válido.
   */
  public static Mono<Void> verifyValues(MovementCredit movement) {
    return Mono.just(movement)
        .filter(m -> movement.getAmount().doubleValue() > VALUE_MIN_ACCOUNT_BANK)
        .filter(m -> movement.getTotalInstallments() > VALUE_MIN_ACCOUNT_BANK)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_VALUE_MIN_MOVEMENT,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Verifica si un pago de cuota es válido.
   *
   * @param paidInstallment El pago de cuota a verificar.
   * @return true si el pago es válido, false si no lo es.
   */
  public static Mono<Void> paymentIsValid(PaidInstallment paidInstallment) {
    return Mono.just(paidInstallment)
        .map(payment -> payment.getAmount() != null && !payment.getAmount().toString().isBlank())
        .flatMap(aBoolean -> {
          if (!aBoolean) {
            return Mono.error(new ErrorResponseException(EX_ERROR_REQUEST, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
          }else{
            return Mono.empty();
          }
        }).then();
  }

}
