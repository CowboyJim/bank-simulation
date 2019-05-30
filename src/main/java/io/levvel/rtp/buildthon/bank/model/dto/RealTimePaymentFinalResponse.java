package io.levvel.rtp.buildthon.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RealTimePaymentFinalResponse {

	RealTimePaymentPostResponse postResponse;
	RealTimePaymentPatchResponse putResponse;
	String paymentId;
	String status;

}
