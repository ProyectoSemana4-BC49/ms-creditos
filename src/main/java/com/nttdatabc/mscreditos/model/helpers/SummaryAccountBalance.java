package com.nttdatabc.mscreditos.model.helpers;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;


/**
 * SummaryAccountBalance
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-03T12:22:34.799694800-05:00[America/Lima]")
public class SummaryAccountBalance {

  private String accountId;

  private BigDecimal balanceAvg;

  public SummaryAccountBalance accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   *
   * @return accountId
   */

  @Schema(name = "account_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("account_id")
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public SummaryAccountBalance balanceAvg(BigDecimal balanceAvg) {
    this.balanceAvg = balanceAvg;
    return this;
  }

  /**
   * Get balanceAvg
   *
   * @return balanceAvg
   */
  @Valid
  @Schema(name = "balanceAvg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("balanceAvg")
  public BigDecimal getBalanceAvg() {
    return balanceAvg;
  }

  public void setBalanceAvg(BigDecimal balanceAvg) {
    this.balanceAvg = balanceAvg;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SummaryAccountBalance summaryAccountBalance = (SummaryAccountBalance) o;
    return Objects.equals(this.accountId, summaryAccountBalance.accountId) &&
        Objects.equals(this.balanceAvg, summaryAccountBalance.balanceAvg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, balanceAvg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SummaryAccountBalance {\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    balanceAvg: ").append(toIndentedString(balanceAvg)).append("\n");
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