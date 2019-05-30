package io.levvel.rtp.buildthon.bank.services;

import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentFinalResponse;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPatchResponse;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPostRequest;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPostResponse;
import io.swagger.model.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PaymentGatewayOperationsImpl implements PaymentGatewayOperations {

	private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayOperationsImpl.class);

	@Value("${apis.immediate.payment.post.url}")
	String realTimePaymentPostUrl;

	@Value("${apis.immediate.payment.patch.url}")
	String realTimePaymentPatchUrl;

	@Value("${apis.rfp.url}")
	String requestForPaymentUrl;

	@Value("${apis.alerts.url}")
	String alertsUrl;

	RestTemplate restTemplate;
	NotificationCallbackService callbackService;

	@Autowired
	public PaymentGatewayOperationsImpl(RestTemplate restTemplate, NotificationCallbackService callbackService) {
		this.restTemplate = restTemplate;
		this.callbackService = callbackService;
	}

	@Override
	public List<Notifications> getNotifications() {
		logger.debug("Executing getNotifications()");

		List<Notifications> result = null;

		try {
			result = this.restTemplate.getForObject(alertsUrl, List.class);
			logger.info("getNotifications() result: {}", result.toString());

		} catch (Exception ex) {
			logger.error("getNotifications() Exception occurred", ex);
			throw ex;
		}
		return result;
	}

	/**
	 * Calls Oracle API to initiate a real time payment
	 *
	 * @param creditTransfer
	 * @return
	 */
	@Override
	public RealTimePaymentFinalResponse sendRealTimePayment(RealTimePaymentPostRequest creditTransfer) {

		RealTimePaymentFinalResponse result = null;

		try {

			RealTimePaymentPostResponse postResponse = postRealTimePayment(creditTransfer);
			RealTimePaymentPatchResponse putResponse = patchRealTimePayment(postResponse.getPaymentId(), postResponse);

			result = new RealTimePaymentFinalResponse();
			result.setPostResponse(postResponse);
			result.setPutResponse(putResponse);
			result.setPaymentId(postResponse.getPaymentId());
			result.setStatus(putResponse.getStatusOfPutOperation());

			callbackService.callRegisteredWebHooks(creditTransfer);

		} catch (Exception ex) {

			logger.error("Real Time Payment Exception occurred", ex);
			throw ex;
		}
		return result;
	}


	/**
	 * @param paymentId
	 * @return
	 */
	public RealTimePaymentPatchResponse patchRealTimePayment(String paymentId, RealTimePaymentPostResponse postResponse) {

		Assert.notNull(paymentId, "Non null payment ID must be supplied");

		// Create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity requestEntity = new HttpEntity("", headers);

		Map<String, String> uriParams = new HashMap<>();
		uriParams.put("paymentId", paymentId);

		logger.debug("------ \n " + requestEntity.toString());

		// Execute REST operation
		ResponseEntity<RealTimePaymentPatchResponse> patchResponse = this.restTemplate.exchange(realTimePaymentPatchUrl,
				HttpMethod.PATCH, requestEntity, RealTimePaymentPatchResponse.class, uriParams);

		logger.debug("putRealTimePayment() request entity: {}", requestEntity.toString());

		return patchResponse.getBody();
	}

	/**
	 * @param creditTransfer
	 * @return
	 */
	public RealTimePaymentPostResponse postRealTimePayment(RealTimePaymentPostRequest creditTransfer) {
		RealTimePaymentPostResponse result = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<RealTimePaymentPostRequest> requestEntity = new HttpEntity<>(creditTransfer, headers);
		logger.debug("postRealTimePayment() request entity: {}", requestEntity.toString());


		ResponseEntity<RealTimePaymentPostResponse> response = this.restTemplate.exchange(realTimePaymentPostUrl,
				HttpMethod.POST, requestEntity, RealTimePaymentPostResponse.class);

		logger.info("postRealTimePayment() HTTP operationStatus: {}", response.getStatusCode());

		result = response.getBody();
		logger.debug("postRealTimePayment() result: {}", result);


		return result;
	}


	@Override
	public Object confirmPayment(Object paymentId) {
		return null;
	}

	@Override
	public Object requestImmediatePayment(Object creditTransfer) {
		return null;
	}

	@Override
	public Object getAccountInfo(String accountNumber) {
		return null;
	}


}
