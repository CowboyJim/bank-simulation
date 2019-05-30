package io.levvel.rtp.buildthon.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RealTimePaymentPostResponse {

	String paymentId;
	String payeeId;
	String groupId;
	String paymentType;

	//String operationStatus;
}
