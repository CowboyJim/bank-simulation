package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WebHookCallback
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-28T15:52:11.162Z[GMT]")
public class WebHookCallback   {
  @JsonProperty("url")
  private String url = null;

  @JsonProperty("accountNumber")
  private String accountNumber = null;

  @JsonProperty("name")
  private String name = null;

  public WebHookCallback url(String url) {
    this.url = url;
    return this;
  }

  /**
   * complete URL including port
   * @return url
  **/
  @ApiModelProperty(value = "complete URL including port")

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public WebHookCallback accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * account number that is receiving a payment
   * @return accountNumber
  **/
  @ApiModelProperty(value = "account number that is receiving a payment")

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public WebHookCallback name(String name) {
    this.name = name;
    return this;
  }

  /**
   * name of the account
   * @return name
  **/
  @ApiModelProperty(value = "name of the account")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebHookCallback webHookCallback = (WebHookCallback) o;
    return Objects.equals(this.url, webHookCallback.url) &&
        Objects.equals(this.accountNumber, webHookCallback.accountNumber) &&
        Objects.equals(this.name, webHookCallback.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, accountNumber, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WebHookCallback {\n");
    
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
