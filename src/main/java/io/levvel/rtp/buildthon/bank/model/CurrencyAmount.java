package io.levvel.rtp.buildthon.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
public class CurrencyAmount {
	String currency="USD";

	@NotNull
	Number amount;
}
