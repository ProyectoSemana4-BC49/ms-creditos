package com.nttdatabc.mscreditos.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Customer Ex.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerExt {
  private String _id;

  private String identifier;

  private String fullname;

  private String type;

  private String address;

  private String phone;

  private String email;

  private String birthday;

  private List<AuthorizedSignerExt> authorizedSigners;
}
