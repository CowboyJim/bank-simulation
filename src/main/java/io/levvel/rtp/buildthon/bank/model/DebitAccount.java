package io.levvel.rtp.buildthon.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DebitAccount {
	String displayValue="xxxxxxxxxx0343043";
	String value;
}
