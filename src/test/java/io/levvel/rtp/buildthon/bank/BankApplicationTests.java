package io.levvel.rtp.buildthon.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.levvel.rtp.buildthon.bank.model.CurrencyAmount;
import io.levvel.rtp.buildthon.bank.model.DebitAccount;
import io.levvel.rtp.buildthon.bank.model.RecipientAccount;
import io.levvel.rtp.buildthon.bank.model.dto.*;
import io.levvel.rtp.buildthon.bank.services.AuthenticationCredentialsService;
import io.levvel.rtp.buildthon.bank.services.PaymentGatewayOperationsImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"username=JasonD", "password=Welcome@1"})
public class BankApplicationTests {

	static final String username = "JasonD";
	static final String password = "Welcome@1";

	@Autowired
	PaymentGatewayOperationsImpl paymentApiOperations;

	@Autowired
	AuthenticationCredentialsService credentialsService;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void getOAuthTokenTest() {

		try {
			TokenRequest request = new TokenRequest();
			request.setUsername(username);
			request.setPassword(password);
			TokenResponse response = credentialsService.getOAuthToken(request);
			System.out.println(response.toString());

		} catch (Exception ex) {
			System.err.println(ex.getStackTrace());
			fail("Authentication failed " + ex.getMessage());
		}
	}

	@Test
	public void realTimePaymentPostTest() {
		try {

			RealTimePaymentPostResponse response = paymentApiOperations.
					postRealTimePayment(getRealTimePaymentRequest());

			System.out.println(response.toString());

		} catch (Exception ex) {
			System.err.println(ex.getStackTrace());
		}
	}

	@Test
	public void realTimePaymentPatchTest(){
		try {

			RealTimePaymentPatchResponse response = paymentApiOperations.patchRealTimePayment("WEV4GGJWOD", null);

			System.out.println(response.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}


	}

	@Test
	public void getOnscreenNotificationsTest() {

//		RealTimePaymentResponse response = paymentApiOperations.
//				getNotifications();

	}

	public static RealTimePaymentPostRequest getRealTimePaymentRequest() {

		RealTimePaymentPostRequest request = new RealTimePaymentPostRequest();

		RecipientAccount recipientAccount = new RecipientAccount();
		recipientAccount.setAccountNumber("CIBTEAM120001");
		recipientAccount.setAccountName("Martin Everhart");
		recipientAccount.setNetwork("021000020");
		recipientAccount.setName("Hines");
		recipientAccount.setNickName("Rosco");
		recipientAccount.setTransferMode("ACC");

		CurrencyAmount currencyAmount = new CurrencyAmount();
		currencyAmount.setAmount(50.00);

		DebitAccount debitAccount = new DebitAccount();
		debitAccount.setValue("NYCTEAM120002");
		debitAccount.setDisplayValue("xxxxxxx20002");

		Payment payment = new Payment();
		payment.setCurrencyAmount(currencyAmount);
		payment.setDebitAccount(debitAccount);
		payment.setRemarks("invoice432434324");

		request.setRecipientAccount(recipientAccount);
		request.setPayment(payment);

		return request;
	}

}
