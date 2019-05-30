package io.levvel.rtp.buildthon.bank.services;

import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentFinalResponse;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPatchResponse;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPostRequest;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPostResponse;
import io.swagger.model.Notifications;

import java.util.List;

public interface PaymentGatewayOperations {

	/**
	 *
	 * @param creditTransfer
	 * @return
	 */
	RealTimePaymentPostResponse postRealTimePayment(RealTimePaymentPostRequest creditTransfer);

	/**
	 *
	 * @param paymentId
	 * @param postResponse
	 * @return
	 */
	RealTimePaymentPatchResponse patchRealTimePayment(String paymentId, RealTimePaymentPostResponse postResponse);

	/**
	 *
	 * @param creditTransfer
	 * @return
	 */
	RealTimePaymentFinalResponse sendRealTimePayment(RealTimePaymentPostRequest creditTransfer);


	/**
	 *
	 * @return
	 */
	List<Notifications> getNotifications();


	Object confirmPayment(Object paymentId);

	Object requestImmediatePayment(Object creditTransfer);

	Object getAccountInfo(String accountNumber);




}
