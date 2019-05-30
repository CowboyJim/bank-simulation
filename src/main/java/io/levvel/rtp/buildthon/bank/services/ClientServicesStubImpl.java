package io.levvel.rtp.buildthon.bank.services;

import io.swagger.model.Notifications;
import io.swagger.model.Payments;
import io.swagger.model.SendPaymentReponse;
import io.swagger.model.SendPaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
@Profile("test")
public class ClientServicesStubImpl implements SwaggerGeneratedApis {

	private static final Logger logger = LoggerFactory.getLogger(ClientServicesStubImpl.class);


	@Autowired
	public ClientServicesStubImpl() {
		logger.info("Creating ** TEST ** client services");
	}

	@Override
	public ResponseEntity<List<Notifications>> getNotification() {
		logger.debug("In getNotifications()");
		return ok(getTestNotifications());
	}

	@Override
	public ResponseEntity<List<Payments>> getPayments(String type) {
		logger.debug("In getPayments()");
		return ok(getPayments());
	}

	@Override
	public ResponseEntity<SendPaymentReponse> sendPayment(@Valid SendPaymentRequest body) {

		StringBuilder sb = new StringBuilder();
		sb.append("Posted params:");
		sb.append("\nAccount number: " + body.getAccountNumber());
		sb.append("\nInvoice number: " + body.getInvoiceNumber());
		sb.append("\nAmount: " + body.getAmount());

		logger.info(sb.toString());

		SendPaymentReponse response = new SendPaymentReponse();
		response.setPaymentId("test_payment_id");
		response.setOperationResult("SUCCESS");

		return ok(response);
	}

	/**
	 * @return
	 */
	private List<Notifications> getTestNotifications() {
		Notifications notification1 = new Notifications();
		Notifications notification2 = new Notifications();

		List<Notifications> notificationArray = new ArrayList<>();

		notification1.setPaymentId("paymentID_1_43434234234789");
		notification1.setInvoiceNumber("invoiceNumber_1_321312321");
		notification2.setPaymentId("paymentID_2_43434234234789");
		notification2.setInvoiceNumber("invoiceNumber_2_321312321");

		notificationArray.add(notification1);
		return notificationArray;
	}


	/**
	 * @return
	 */
	private List<Payments> getPayments() {

		List<Payments> payments = new ArrayList<>();
		Payments payment1 = new Payments();
		Payments payment2 = new Payments();


		OffsetDateTime time = OffsetDateTime.now(ZoneOffset.of("+02:00"));

		payment1.setInitiationTime(time);
		payment2.setInitiationTime(time);

		payment1.setPaymentId("paymentId_1_45437543895789");
		payment2.setPaymentId("paymentId_1_45437543895789");

		payment1.setStatus(Payments.StatusEnum.SUCCESS);
		payment2.setStatus(Payments.StatusEnum.SUCCESS);

		SendPaymentRequest request1 = new SendPaymentRequest();
		request1.setAccountNumber("account_1_number");
		request1.setAmount(Double.valueOf("26.75"));
		request1.setInvoiceNumber("invoice_1_097450743057408");
		request1.setPaymentType("rtp");
		request1.setRecipientName("Oscar Myer");
		request1.setRoutingNumber("rtn_1_43204390-0-0-");
		payment1.setPaymentData(request1);

		SendPaymentRequest request2 = new SendPaymentRequest();
		request2.setAccountNumber("account_2_number");
		request2.setAmount(Double.valueOf("10000.75"));
		request2.setInvoiceNumber("invoice_2_097450743057408");
		request2.setPaymentType("rtp");
		request2.setRecipientName("Oscar Myer");
		request2.setRoutingNumber("rtn_2_43204432779");
		payment2.setPaymentData(request2);

		payments.add(payment1);
		payments.add(payment2);
		return payments;
	}
}
