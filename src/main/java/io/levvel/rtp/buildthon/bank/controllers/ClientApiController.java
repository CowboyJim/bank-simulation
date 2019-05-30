package io.levvel.rtp.buildthon.bank.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.levvel.rtp.buildthon.bank.services.SwaggerGeneratedApis;
import io.swagger.annotations.ApiParam;
import io.swagger.api.NotificationsApi;
import io.swagger.api.PaymentsApi;
import io.swagger.model.Notifications;
import io.swagger.model.Payments;
import io.swagger.model.SendPaymentReponse;
import io.swagger.model.SendPaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientApiController implements PaymentsApi, NotificationsApi {

	@Value("${spring.application.name}")
	String appName;

	private static final Logger logger = LoggerFactory.getLogger(ClientApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	SwaggerGeneratedApis clientServices ;

	@Autowired
	public ClientApiController(ObjectMapper objectMapper, HttpServletRequest request, SwaggerGeneratedApis clientServices) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.clientServices = clientServices;
	}

	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "home";
	}

	public ResponseEntity<List<Payments>> getPayments(@ApiParam(value = "Type of payment, real time or ach", required = true)
													  @RequestHeader(value = "type", required = true) String type) {
		return clientServices.getPayments(type);
	}

	public ResponseEntity<SendPaymentReponse> sendPayment(@ApiParam(value = "Inventory item to add") @Valid @RequestBody SendPaymentRequest body) {
		return clientServices.sendPayment(body);
	}

	/**
	 * @see
	 * @return
	 */
	@Override
	public ResponseEntity<List<Notifications>> getNotification() {
		return clientServices.getNotification();

	}
}
