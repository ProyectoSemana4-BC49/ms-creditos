package com.nttdatabc.mscreditos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdatabc.mscreditos.model.helpers.PaidInstallment;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MovementCredit
 */
@Document(value = "movementCredit")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-03T12:22:34.799694800-05:00[America/Lima]")
public class MovementCredit {

  private String id;

  private String creditId;

  private Integer totalInstallments;

  private BigDecimal amount;

  private String status;

  private String dayCreated;

  private String dueDate;

  @Valid
  private List<@Valid PaidInstallment> paidInstallments;

  public MovementCredit id(String id) {
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

  public MovementCredit creditId(String creditId) {
    this.creditId = creditId;
    return this;
  }

  /**
   * Get creditId
   * @return creditId
   */

  @Schema(name = "credit_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("credit_id")
  public String getCreditId() {
    return creditId;
  }

  public void setCreditId(String creditId) {
    this.creditId = creditId;
  }

  public MovementCredit totalInstallments(Integer totalInstallments) {
    this.totalInstallments = totalInstallments;
    return this;
  }

  /**
   * Get totalInstallments
   * @return totalInstallments
   */

  @Schema(name = "total_installments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_installments")
  public Integer getTotalInstallments() {
    return totalInstallments;
  }

  public void setTotalInstallments(Integer totalInstallments) {
    this.totalInstallments = totalInstallments;
  }

  public MovementCredit amount(BigDecimal amount) {
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

  public MovementCredit status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */

  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public MovementCredit dayCreated(String dayCreated) {
    this.dayCreated = dayCreated;
    return this;
  }

  /**
   * Get dayCreated
   * @return dayCreated
   */

  @Schema(name = "day_created", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("day_created")
  public String getDayCreated() {
    return dayCreated;
  }

  public void setDayCreated(String dayCreated) {
    this.dayCreated = dayCreated;
  }

  public MovementCredit dueDate(String dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
   */

  @Schema(name = "due_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("due_date")
  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public MovementCredit paidInstallments(List<@Valid PaidInstallment> paidInstallments) {
    this.paidInstallments = paidInstallments;
    return this;
  }

  public MovementCredit addPaidInstallmentsItem(PaidInstallment paidInstallmentsItem) {
    if (this.paidInstallments == null) {
      this.paidInstallments = new ArrayList<>();
    }
    this.paidInstallments.add(paidInstallmentsItem);
    return this;
  }

  /**
   * Get paidInstallments
   * @return paidInstallments
   */
  @Valid
  @Schema(name = "paid_installments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("paid_installments")
  public List<@Valid PaidInstallment> getPaidInstallments() {
    return paidInstallments;
  }

  public void setPaidInstallments(List<@Valid PaidInstallment> paidInstallments) {
    this.paidInstallments = paidInstallments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovementCredit movementCredit = (MovementCredit) o;
    return Objects.equals(this.id, movementCredit.id) &&
        Objects.equals(this.creditId, movementCredit.creditId) &&
        Objects.equals(this.totalInstallments, movementCredit.totalInstallments) &&
        Objects.equals(this.amount, movementCredit.amount) &&
        Objects.equals(this.status, movementCredit.status) &&
        Objects.equals(this.dayCreated, movementCredit.dayCreated) &&
        Objects.equals(this.dueDate, movementCredit.dueDate) &&
        Objects.equals(this.paidInstallments, movementCredit.paidInstallments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, creditId, totalInstallments, amount, status, dayCreated, dueDate, paidInstallments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovementCredit {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    creditId: ").append(toIndentedString(creditId)).append("\n");
    sb.append("    totalInstallments: ").append(toIndentedString(totalInstallments)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    dayCreated: ").append(toIndentedString(dayCreated)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    paidInstallments: ").append(toIndentedString(paidInstallments)).append("\n");
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

