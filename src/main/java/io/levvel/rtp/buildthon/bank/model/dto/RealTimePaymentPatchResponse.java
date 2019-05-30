package io.levvel.rtp.buildthon.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RealTimePaymentPatchResponse {

	String statusOfPutOperation = "notSet";
	String uniqueEndToEndTxnReference;
	String externalReferenceId;

	@SuppressWarnings("unchecked")
	@JsonProperty("status")
	void unpackNested(Map<String, Object> message) {
		this.statusOfPutOperation = (String) message.get("result");
	}

}
