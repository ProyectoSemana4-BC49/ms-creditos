package com.nttdatabc.mscreditos.model.helpers;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;


/**
 * BalanceAccounts
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-03T12:22:34.799694800-05:00[America/Lima]")
public class BalanceAccounts {

  private String customerId;

  @Valid
  private List<@Valid SummaryAccountBalance> summaryAccounts;

  public BalanceAccounts customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
   */

  @Schema(name = "customerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public BalanceAccounts summaryAccounts(List<@Valid SummaryAccountBalance> summaryAccounts) {
    this.summaryAccounts = summaryAccounts;
    return this;
  }

  public BalanceAccounts addSummaryAccountsItem(SummaryAccountBalance summaryAccountsItem) {
    if (this.summaryAccounts == null) {
      this.summaryAccounts = new ArrayList<>();
    }
    this.summaryAccounts.add(summaryAccountsItem);
    return this;
  }

  /**
   * Get summaryAccounts
   * @return summaryAccounts
   */
  @Valid
  @Schema(name = "summary_accounts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("summary_accounts")
  public List<@Valid SummaryAccountBalance> getSummaryAccounts() {
    return summaryAccounts;
  }

  public void setSummaryAccounts(List<@Valid SummaryAccountBalance> summaryAccounts) {
    this.summaryAccounts = summaryAccounts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceAccounts balanceAccounts = (BalanceAccounts) o;
    return Objects.equals(this.customerId, balanceAccounts.customerId) &&
        Objects.equals(this.summaryAccounts, balanceAccounts.summaryAccounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, summaryAccounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceAccounts {\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    summaryAccounts: ").append(toIndentedString(summaryAccounts)).append("\n");
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

