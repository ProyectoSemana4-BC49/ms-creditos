package com.nttdatabc.mscreditos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Ext Authorized Signer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedSignerExt {
  private String dni;
  private String fullname;
  private String cargo;
}
