package io.levvel.rtp.buildthon.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.levvel.rtp.buildthon.bank.model.CurrencyAmount;
import io.levvel.rtp.buildthon.bank.model.DebitAccount;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Payment {

	@JsonProperty("amount")
	CurrencyAmount currencyAmount;

	@JsonProperty("debitAccountId")
	DebitAccount debitAccount;

	String remarks;

}
