package com.nttdatabc.mscreditos.utils;

import com.google.gson.Gson;
import com.nttdatabc.mscreditos.model.response.CustomerExt;
import java.util.UUID;

/**
 * Utilitarios.
 */
public class Utilitarios {
  public static String generateUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static CustomerExt convertStrToCustomerExt(String response){
    Gson gson = new Gson();
    return gson.fromJson(response, CustomerExt.class);
  }
}
