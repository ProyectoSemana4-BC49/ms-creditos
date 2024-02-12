package com.nttdatabc.mscreditos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Credit
 */

@Document(value = "credit")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-03T12:22:34.799694800-05:00[America/Lima]")
public class Credit {

  private String id;

  private String customerId;

  private String typeCredit;

  private BigDecimal mountLimit;

  private String dateOpen;

  private BigDecimal interestRate;

  public Credit id(String id) {
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

  public Credit customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
   */

  @Schema(name = "customer_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customer_id")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Credit typeCredit(String typeCredit) {
    this.typeCredit = typeCredit;
    return this;
  }

  /**
   * Get typeCredit
   * @return typeCredit
   */

  @Schema(name = "type_credit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type_credit")
  public String getTypeCredit() {
    return typeCredit;
  }

  public void setTypeCredit(String typeCredit) {
    this.typeCredit = typeCredit;
  }

  public Credit mountLimit(BigDecimal mountLimit) {
    this.mountLimit = mountLimit;
    return this;
  }

  /**
   * Get mountLimit
   * @return mountLimit
   */
  @Valid
  @Schema(name = "mount_limit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mount_limit")
  public BigDecimal getMountLimit() {
    return mountLimit;
  }

  public void setMountLimit(BigDecimal mountLimit) {
    this.mountLimit = mountLimit;
  }

  public Credit dateOpen(String dateOpen) {
    this.dateOpen = dateOpen;
    return this;
  }

  /**
   * Get dateOpen
   * @return dateOpen
   */

  @Schema(name = "date_open", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date_open")
  public String getDateOpen() {
    return dateOpen;
  }

  public void setDateOpen(String dateOpen) {
    this.dateOpen = dateOpen;
  }

  public Credit interestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
    return this;
  }

  /**
   * Get interestRate
   * @return interestRate
   */
  @Valid
  @Schema(name = "interest_rate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("interest_rate")
  public BigDecimal getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Credit credit = (Credit) o;
    return Objects.equals(this.id, credit.id) &&
        Objects.equals(this.customerId, credit.customerId) &&
        Objects.equals(this.typeCredit, credit.typeCredit) &&
        Objects.equals(this.mountLimit, credit.mountLimit) &&
        Objects.equals(this.dateOpen, credit.dateOpen) &&
        Objects.equals(this.interestRate, credit.interestRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerId, typeCredit, mountLimit, dateOpen, interestRate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Credit {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    typeCredit: ").append(toIndentedString(typeCredit)).append("\n");
    sb.append("    mountLimit: ").append(toIndentedString(mountLimit)).append("\n");
    sb.append("    dateOpen: ").append(toIndentedString(dateOpen)).append("\n");
    sb.append("    interestRate: ").append(toIndentedString(interestRate)).append("\n");
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
