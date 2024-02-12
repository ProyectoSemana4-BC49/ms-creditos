package com.nttdatabc.mscreditos.model.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;

/**
 * PaidInstallment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-03T12:22:34.799694800-05:00[America/Lima]")
public class PaidInstallment {

  private String id;

  private String datePayment;

  private BigDecimal amount;

  private Integer installmentNumber;

  public PaidInstallment id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */

  @Schema(name = "_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("_id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PaidInstallment datePayment(String datePayment) {
    this.datePayment = datePayment;
    return this;
  }

  /**
   * Get datePayment
   * @return datePayment
   */

  @Schema(name = "date_payment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date_payment")
  public String getDatePayment() {
    return datePayment;
  }

  public void setDatePayment(String datePayment) {
    this.datePayment = datePayment;
  }

  public PaidInstallment amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   */
  @Valid
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaidInstallment installmentNumber(Integer installmentNumber) {
    this.installmentNumber = installmentNumber;
    return this;
  }

  /**
   * Get installmentNumber
   * @return installmentNumber
   */

  @Schema(name = "installment_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("installment_number")
  public Integer getInstallmentNumber() {
    return installmentNumber;
  }

  public void setInstallmentNumber(Integer installmentNumber) {
    this.installmentNumber = installmentNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaidInstallment paidInstallment = (PaidInstallment) o;
    return Objects.equals(this.id, paidInstallment.id) &&
        Objects.equals(this.datePayment, paidInstallment.datePayment) &&
        Objects.equals(this.amount, paidInstallment.amount) &&
        Objects.equals(this.installmentNumber, paidInstallment.installmentNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, datePayment, amount, installmentNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaidInstallment {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    datePayment: ").append(toIndentedString(datePayment)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    installmentNumber: ").append(toIndentedString(installmentNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}