package com.nttdatabc.mscreditos.controller.interfaces;

import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.helpers.PaidInstallment;
import com.nttdatabc.mscreditos.utils.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-03T12:22:34.799694800-05:00[America/Lima]")
@Validated
@Tag(name = "Movimientos Créditos", description = "the Movimientos Créditos API")
public interface MovementControllerApi {

  /**
   * POST /movement_credits : Crear un nuevo movimiento de crédito
   *
   * @param movementCredit  (required)
   * @return Movimiento de crédito creado exitosamente (status code 201)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "createMovementCredit",
      summary = "Crear un nuevo movimiento de crédito",
      tags = { "Movimientos Créditos" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Movimiento de crédito creado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement_credits",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createMovementCredit(
      @Parameter(name = "MovementCredit", description = "", required = true) @Valid @RequestBody MovementCredit movementCredit,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * POST /movement_credits/paid_installments/{movement_id} : Pagar una cuota de un movimiento de crédito
   *
   * @param movementId  (required)
   * @param paidInstallment  (required)
   * @return Cuota pagada exitosamente (status code 201)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "createPaymentInstallmentByMovementId",
      summary = "Pagar una cuota de un movimiento de crédito",
      tags = { "Movimientos Créditos" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Cuota pagada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement_credits/paid_installments/{movement_id}",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createPaymentInstallmentByMovementId(
      @Parameter(name = "movement_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("movement_id") String movementId,
      @Parameter(name = "PaidInstallment", description = "", required = true) @Valid @RequestBody PaidInstallment paidInstallment,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * DELETE /movement_credits/{movement_id} : Eliminar un movimiento de crédito
   *
   * @param movementId  (required)
   * @return Movimiento de crédito eliminado exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "deleteMovementCredit",
      summary = "Eliminar un movimiento de crédito",
      tags = { "Movimientos Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Movimiento de crédito eliminado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.DELETE,
      value = "/movement_credits/{movement_id}"
  )
  default ResponseEntity<Mono<Void>> deleteMovementCredit(
      @Parameter(name = "movement_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("movement_id") String movementId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * GET /movement_credits/{movement_id} : Obtener un movimiento de crédito por ID
   *
   * @param movementId  (required)
   * @return Movimiento de crédito obtenido exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getMovementCreditById",
      summary = "Obtener un movimiento de crédito por ID",
      tags = { "Movimientos Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Movimiento de crédito obtenido exitosamente", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = MovementCredit.class))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/movement_credits/{movement_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Mono<MovementCredit>> getMovementCreditById(
      @Parameter(name = "movement_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("movement_id") String movementId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "{ \"amount\" : 6.027456183070403, \"credit_id\" : \"credit_id\", \"day_created\" : \"day_created\", \"total_installments\" : 0, \"due_date\" : \"due_date\", \"_id\" : \"_id\", \"paid_installments\" : [ { \"amount\" : 1.4658129805029452, \"installment_number\" : 5, \"date_payment\" : \"date_payment\", \"_id\" : \"_id\" }, { \"amount\" : 1.4658129805029452, \"installment_number\" : 5, \"date_payment\" : \"date_payment\", \"_id\" : \"_id\" } ], \"status\" : \"status\" }";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /movement_credits/credit/{credit_id} : Obtener los movimientos por ID de crédito
   *
   * @param creditId  (required)
   * @return Lista de movimientos obtenida exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getMovementsCreditsByCreditId",
      summary = "Obtener los movimientos por ID de crédito",
      tags = { "Movimientos Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovementCredit.class)))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/movement_credits/credit/{credit_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<MovementCredit>> getMovementsCreditsByCreditId(
      @Parameter(name = "credit_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("credit_id") String creditId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"amount\" : 6.027456183070403, \"credit_id\" : \"credit_id\", \"day_created\" : \"day_created\", \"total_installments\" : 0, \"due_date\" : \"due_date\", \"_id\" : \"_id\", \"paid_installments\" : [ { \"amount\" : 1.4658129805029452, \"installment_number\" : 5, \"date_payment\" : \"date_payment\", \"_id\" : \"_id\" }, { \"amount\" : 1.4658129805029452, \"installment_number\" : 5, \"date_payment\" : \"date_payment\", \"_id\" : \"_id\" } ], \"status\" : \"status\" }, { \"amount\" : 6.027456183070403, \"credit_id\" : \"credit_id\", \"day_created\" : \"day_created\", \"total_installments\" : 0, \"due_date\" : \"due_date\", \"_id\" : \"_id\", \"paid_installments\" : [ { \"amount\" : 1.4658129805029452, \"installment_number\" : 5, \"date_payment\" : \"date_payment\", \"_id\" : \"_id\" }, { \"amount\" : 1.4658129805029452, \"installment_number\" : 5, \"date_payment\" : \"date_payment\", \"_id\" : \"_id\" } ], \"status\" : \"status\" } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * PUT /movement_credits : Actualizar un movimiento de crédito existente
   *
   * @param movementCredit  (required)
   * @return Movimiento de crédito actualizado exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "updateMovementCredit",
      summary = "Actualizar un movimiento de crédito existente",
      tags = { "Movimientos Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Movimiento de crédito actualizado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/movement_credits",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> updateMovementCredit(
      @Parameter(name = "MovementCredit", description = "", required = true) @Valid @RequestBody MovementCredit movementCredit,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }

}
