package io.levvel.rtp.buildthon.bank.services;

import io.levvel.rtp.buildthon.bank.model.CurrencyAmount;
import io.levvel.rtp.buildthon.bank.model.DebitAccount;
import io.levvel.rtp.buildthon.bank.model.RecipientAccount;
import io.levvel.rtp.buildthon.bank.model.dto.Payment;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentFinalResponse;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPostRequest;
import io.swagger.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Service
@Profile("!test")
public class ClientServicesImpl implements SwaggerGeneratedApis {

	private static final Logger logger = LoggerFactory.getLogger(ClientServicesImpl.class);

	@Value("${customer.account_1}")
	String debitAccount;

	@Value("${customer.name}")
	String customerName;

	PaymentGatewayOperations gatewayOperations;

	NotificationCallbackService callbackService;

	@Autowired
	public ClientServicesImpl(PaymentGatewayOperations gatewayOperations,
							  NotificationCallbackService callbackService) {
		logger.info("Creating production client services");
		this.gatewayOperations = gatewayOperations;
		this.callbackService = callbackService;
	}


	@Override
	public ResponseEntity<List<Notifications>> getNotification() {

		List<Notifications> result;

		List<CallbackNotification> notifications = callbackService.getCallbackNotifications();

		if (!notifications.isEmpty()) {

			List<Notifications> callbackResult = new ArrayList<>();
			for (CallbackNotification callback : notifications) {
				callbackResult.add(getNotification(callback));
			}

			result = callbackResult;
			logger.info("Returning callbacks received through the server WebHook");

		} else {
			// Bypass Oracle notifications since they can never work for this denmo
			if (true) {
				result = new ArrayList<>();
			}
			try {
				result = gatewayOperations.getNotifications();

			} catch (Exception ex) {

				logger.error("Exception in getNotification()", ex);
				return ResponseEntity
						.status(HttpStatus.BAD_GATEWAY).build();
			}
		}
		return ResponseEntity.ok(result);
	}

	private Notifications getNotification(CallbackNotification callback) {
		Notifications notification = new Notifications();
		notification.setInvoiceNumber(callback.getInvoiceNumber());
		return notification;
	}

	@Override
	//TODO Implement
	public ResponseEntity<List<Payments>> getPayments(String type) {
		return null;
	}

	@Override
	public ResponseEntity<SendPaymentReponse> sendPayment(@Valid SendPaymentRequest body) {

		RealTimePaymentPostRequest request = new RealTimePaymentPostRequest();

		// Create payment
		CurrencyAmount amount = new CurrencyAmount();
		amount.setAmount(body.getAmount());

		DebitAccount debit = new DebitAccount();
		debit.setValue(debitAccount);

		Payment payment = new Payment();
		payment.setCurrencyAmount(amount);
		payment.setDebitAccount(debit);
		payment.setRemarks(body.getInvoiceNumber());

		RecipientAccount recipientAccount = new RecipientAccount();
		recipientAccount.setAccountNumber(body.getAccountNumber());
		recipientAccount.setNetwork(body.getRoutingNumber());
		recipientAccount.setAccountName(body.getRecipientName());
		recipientAccount.setName("Hines");
		recipientAccount.setNickName("Catsup supplier");

		request.setPayment(payment);
		request.setRecipientAccount(recipientAccount);

		RealTimePaymentFinalResponse response = gatewayOperations.sendRealTimePayment(request);
		logger.info("Real time payment response: {}", response.toString());

		SendPaymentReponse clientResponse = new SendPaymentReponse();
		clientResponse.setPaymentId(response.getPaymentId());
		clientResponse.setOperationResult(response.getStatus());

		return ResponseEntity.ok(clientResponse);
	}
}
