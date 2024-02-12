package com.nttdatabc.mscreditos.utils;

/**
 * Clase constante.
 */
public class Constantes {
  public static final String PREFIX_PATH = "/api/v1";
  public static final String EX_ERROR_REQUEST = "Error en uno de los parámetros";
  public static final String EX_ERROR_TYPE_ACCOUNT = "Recuerda que solo existe tipo PERSONAL | EMPRESA | TARJETA";
  public static final String EX_VALUE_EMPTY = "Uno de los parámetros viene vacío";
  public static final String EX_NOT_FOUND_RECURSO = "No existe el recurso";
  public static final String EX_ERROR_VALUE_MIN = "El valor es el mínimo para abrir un producto crediticio";
  public static final String EX_ERROR_VALUE_MIN_MOVEMENT = "El valor es el mínimo para realizar un movimiento";
  public static Double VALUE_MIN_ACCOUNT_BANK = 0.0;
  public static final Double INTEREST_RATE = 12.5;
  public static final String EX_ERROR_CONFLICTO_CUSTOMER_PERSONA = "Este usuario ya tiene registrado algún crédito.";
  public static final Integer MAX_SIZE_ACCOUNT_CUSTOMER_PERSONA = 1;
  public static final String EX_ERROR_AMOUNT_CREDIT = "El monto solicitado, no puede ser igual o mayor al monto límite";
  public static final String EX_ERROR_INSTALLMENTS = "Se puede solicitar un máximo de 10 cuotas";
  public static final Integer MAX_SIZE_INSTALLMENTS = 10;
  public static final String EX_ERROR_PAYMENT_LIMIT = "Ya canceló todas las cuotas.";
  public static final String URL_CUSTOMER_ID = "http://localhost:8080/api/v1/customer/";
  public static final String EX_ERROR_NOT_CARD_CREDIT = "El reporte solo es válido para tarjetas de crédito.";
}

