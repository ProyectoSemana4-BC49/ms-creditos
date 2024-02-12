package com.nttdatabc.mscreditos.controller.interfaces;

import com.nttdatabc.mscreditos.model.Credit;
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
@Tag(name = "Créditos", description = "the Créditos API")
public interface CreditControllerApi {

  /**
   * POST /credits : Insertar un nuevo crédito
   *
   * @param credit  (required)
   * @return Crédito insertado exitosamente (status code 201)
   *         or Error en request (status code 400)
   */
  @Operation(
      operationId = "createCredit",
      summary = "Insertar un nuevo crédito",
      tags = { "Créditos" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Crédito insertado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/credits",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createCredit(
      @Parameter(name = "Credit", description = "", required = true) @Valid @RequestBody Credit credit,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * DELETE /credits/{credit_id} : Eliminar un crédito por ID
   *
   * @param creditId  (required)
   * @return Crédito eliminado exitosamente (status code 200)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "deleteCreditById",
      summary = "Eliminar un crédito por ID",
      tags = { "Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Crédito eliminado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.DELETE,
      value = "/credits/{credit_id}"
  )
  default ResponseEntity<Mono<Void>> deleteCreditById(
      @Parameter(name = "credit_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("credit_id") String creditId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * GET /credits : Obtener todos los créditos
   *
   * @return Lista de créditos obtenida exitosamente (status code 200)
   */
  @Operation(
      operationId = "getAllCredits",
      summary = "Obtener todos los créditos",
      tags = { "Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de créditos obtenida exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Credit.class)))
          })
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/credits",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<Credit>> getAllCredits(
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"date_open\" : \"date_open\", \"mount_limit\" : 0.8008281904610115, \"interest_rate\" : 6.027456183070403, \"type_credit\" : \"type_credit\", \"_id\" : \"_id\", \"customer_id\" : \"customer_id\" }, { \"date_open\" : \"date_open\", \"mount_limit\" : 0.8008281904610115, \"interest_rate\" : 6.027456183070403, \"type_credit\" : \"type_credit\", \"_id\" : \"_id\", \"customer_id\" : \"customer_id\" } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /credits/{credit_id} : Trae información de un crédito según su credit_id
   *
   * @param creditId  (required)
   * @return Crédito obtenido correctamente (status code 200)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getCreditById",
      summary = "Trae información de un crédito según su credit_id",
      tags = { "Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Crédito obtenido correctamente", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = Credit.class))
          }),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/credits/{credit_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Mono<Credit>> getCreditById(
      @Parameter(name = "credit_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("credit_id") String creditId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "{ \"date_open\" : \"date_open\", \"mount_limit\" : 0.8008281904610115, \"interest_rate\" : 6.027456183070403, \"type_credit\" : \"type_credit\", \"_id\" : \"_id\", \"customer_id\" : \"customer_id\" }";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /credits/customer/{customer_id} : Obtener los créditos por ID de cliente
   *
   * @param customerId  (required)
   * @return Lista de créditos obtenida exitosamente (status code 200)
   */
  @Operation(
      operationId = "getCreditsByCustomerId",
      summary = "Obtener los créditos por ID de cliente",
      tags = { "Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de créditos obtenida exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Credit.class)))
          })
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/credits/customer/{customer_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<Credit>> getCreditsByCustomerId(
      @Parameter(name = "customer_id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("customer_id") String customerId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"date_open\" : \"date_open\", \"mount_limit\" : 0.8008281904610115, \"interest_rate\" : 6.027456183070403, \"type_credit\" : \"type_credit\", \"_id\" : \"_id\", \"customer_id\" : \"customer_id\" }, { \"date_open\" : \"date_open\", \"mount_limit\" : 0.8008281904610115, \"interest_rate\" : 6.027456183070403, \"type_credit\" : \"type_credit\", \"_id\" : \"_id\", \"customer_id\" : \"customer_id\" } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * PUT /credits : Actualizar un crédito existente
   *
   * @param credit  (required)
   * @return Actualizado correctamente (status code 200)
   *         or Error en el request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "updateCredit",
      summary = "Actualizar un crédito existente",
      tags = { "Créditos" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Actualizado correctamente"),
          @ApiResponse(responseCode = "400", description = "Error en el request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/credits",
      consumes = { "application/json" }
  )
  default ResponseEntity<Void> updateCredit(
      @Parameter(name = "Credit", description = "", required = true) @Valid @RequestBody Credit credit,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }

}