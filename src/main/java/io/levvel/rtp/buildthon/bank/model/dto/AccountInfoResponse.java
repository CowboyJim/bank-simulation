package io.levvel.rtp.buildthon.bank.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.levvel.rtp.buildthon.bank.model.CurrencyAmount;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AccountInfoResponse {

	CurrencyAmount amountInAccountCurrency;


}
