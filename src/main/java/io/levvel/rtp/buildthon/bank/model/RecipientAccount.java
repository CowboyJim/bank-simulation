package io.levvel.rtp.buildthon.bank.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RecipientAccount {
	String accountNumber;
	String accountName;
	String name;
	String nickName;
	String network;
	String transferMode="ACC";

}
