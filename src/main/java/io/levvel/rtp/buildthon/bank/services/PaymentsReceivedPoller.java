package io.levvel.rtp.buildthon.bank.services;


import io.swagger.model.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentsReceivedPoller {

	@Value("${disable.payment.polling}")
	boolean disablePolling;

	PaymentGatewayOperations gatewayOperations;

	LocalDateTime lastCheck = LocalDateTime.now();

	@Autowired
	public PaymentsReceivedPoller(PaymentGatewayOperations gatewayOperations) {
		this.gatewayOperations = gatewayOperations;
	}

	public void pollForNewPayments() {

		if (disablePolling) return;

		// Poll to see if any new payments have been received
		List<Notifications> notificationsList = gatewayOperations.getNotifications();

		for (Notifications notification : notificationsList) {

			// Check to see if this notification is newer than the last checked
			// If not, continue, if so, find the new payment invoice ID

		}
		// If they have, send them to all registered webhooks

	}
}
