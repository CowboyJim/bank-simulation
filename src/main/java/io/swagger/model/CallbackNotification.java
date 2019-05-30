package io.swagger.model;


import lombok.Data;

@Data
public class CallbackNotification {

	String accountName;
	String accountNumber;
	String invoiceNumber;
	Number amount;
}
