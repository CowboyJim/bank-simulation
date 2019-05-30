package io.levvel.rtp.buildthon.bank.services;

import io.levvel.rtp.buildthon.bank.model.WebHook;
import io.levvel.rtp.buildthon.bank.model.WebHookRepository;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPostRequest;
import io.swagger.model.CallbackNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationCallbackService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationCallbackService.class);
	private List<CallbackNotification> notifications;

	private WebHookRepository webHookRepository;
	RestTemplate unsecuredRestTemplate;

	@Autowired
	public NotificationCallbackService(WebHookRepository webHookRepository) {
		notifications = new ArrayList();
		this.webHookRepository = webHookRepository;
		this.unsecuredRestTemplate = new RestTemplate();
	}

	public void addReceivedCallback(CallbackNotification notification) {
		notifications.add(notification);
	}

	public List<CallbackNotification> getCallbackNotifications() {
		return notifications;
	}

	public List<WebHook> getWebHooks() {
		return webHookRepository.findAll();
	}

	public void callRegisteredWebHooks(RealTimePaymentPostRequest creditTransfer) {

		List<WebHook> webHooks = getWebHooks();

		if (!webHooks.isEmpty()) {

			for (WebHook webHook : webHooks) {
				String registeredAccountNumber = webHook.getAccountNumber();
				logger.debug("Registered Webhook account number: {}", registeredAccountNumber);

				if (registeredAccountNumber.equalsIgnoreCase(creditTransfer.getRecipientAccount().getAccountNumber())) {

					logger.info("Sending notification to registered webhook URL {}", webHook.getUrl());

					CallbackNotification notification = new CallbackNotification();

					notification.setAmount(creditTransfer.getPayment().getCurrencyAmount().getAmount());
					notification.setInvoiceNumber(creditTransfer.getPayment().getRemarks());
					notification.setAccountNumber(creditTransfer.getRecipientAccount().getAccountNumber());
					notification.setAccountName(creditTransfer.getRecipientAccount().getAccountName());
					try {
						// Create headers and request
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						HttpEntity<CallbackNotification> requestEntity = new HttpEntity(notification, headers);

						logger.debug("Request Entity: {}", requestEntity.toString());
						logger.debug("URL: {}", webHook.getUrl());

						ResponseEntity<String> result = this.unsecuredRestTemplate.exchange(webHook.getUrl(),
								HttpMethod.POST, requestEntity, String.class);

						logger.info("Response from webhook callback: {}", result.getBody());

					} catch (Exception ex) {
						logger.error("Exception calling webhook. URL: {}", webHook.getUrl(), ex);
					}
				}
			}
		}
	}
}
