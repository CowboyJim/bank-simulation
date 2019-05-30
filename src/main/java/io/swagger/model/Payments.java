package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Payments
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-27T17:28:26.140Z[GMT]")
public class Payments   {
  @JsonProperty("paymentData")
  private SendPaymentRequest paymentData = null;

  @JsonProperty("paymentId")
  private String paymentId = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    SUCCESS("success"),
    
    FAILED("failed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("initiationTime")
  private OffsetDateTime initiationTime = null;

  public Payments paymentData(SendPaymentRequest paymentData) {
    this.paymentData = paymentData;
    return this;
  }

  /**
   * Get paymentData
   * @return paymentData
  **/
  @ApiModelProperty(value = "")

  @Valid
  public SendPaymentRequest getPaymentData() {
    return paymentData;
  }

  public void setPaymentData(SendPaymentRequest paymentData) {
    this.paymentData = paymentData;
  }

  public Payments paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  /**
   * Get paymentId
   * @return paymentId
  **/
  @ApiModelProperty(value = "")

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public Payments status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Payments initiationTime(OffsetDateTime initiationTime) {
    this.initiationTime = initiationTime;
    return this;
  }

  /**
   * Get initiationTime
   * @return initiationTime
  **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getInitiationTime() {
    return initiationTime;
  }

  public void setInitiationTime(OffsetDateTime initiationTime) {
    this.initiationTime = initiationTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payments payments = (Payments) o;
    return Objects.equals(this.paymentData, payments.paymentData) &&
        Objects.equals(this.paymentId, payments.paymentId) &&
        Objects.equals(this.status, payments.status) &&
        Objects.equals(this.initiationTime, payments.initiationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentData, paymentId, status, initiationTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payments {\n");
    
    sb.append("    paymentData: ").append(toIndentedString(paymentData)).append("\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    initiationTime: ").append(toIndentedString(initiationTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
