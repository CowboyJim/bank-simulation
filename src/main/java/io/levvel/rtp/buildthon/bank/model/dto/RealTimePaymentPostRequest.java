package io.levvel.rtp.buildthon.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.levvel.rtp.buildthon.bank.model.RecipientAccount;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RealTimePaymentPostRequest {

	@JsonProperty("genericPayee")
	RecipientAccount recipientAccount;

	@JsonProperty("genericPayout")
	Payment payment;

	String paymentType = "USPAYMENTS";

}
