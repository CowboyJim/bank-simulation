package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * SendPaymentReponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-27T17:28:26.140Z[GMT]")
public class SendPaymentReponse   {
  @JsonProperty("paymentId")
  private String paymentId = null;

  @JsonProperty("operationResult")
  private String operationResult = null;

  public SendPaymentReponse paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  /**
   * Get paymentId
   * @return paymentId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public SendPaymentReponse operationResult(String operationResult) {
    this.operationResult = operationResult;
    return this;
  }

  /**
   * Get operationResult
   * @return operationResult
  **/
  @ApiModelProperty(value = "")

  public String getOperationResult() {
    return operationResult;
  }

  public void setOperationResult(String operationResult) {
    this.operationResult = operationResult;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SendPaymentReponse sendPaymentReponse = (SendPaymentReponse) o;
    return Objects.equals(this.paymentId, sendPaymentReponse.paymentId) &&
        Objects.equals(this.operationResult, sendPaymentReponse.operationResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId, operationResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SendPaymentReponse {\n");
    
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    operationResult: ").append(toIndentedString(operationResult)).append("\n");
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
