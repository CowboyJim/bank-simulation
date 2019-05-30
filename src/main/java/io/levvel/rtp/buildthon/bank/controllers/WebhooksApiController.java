package io.levvel.rtp.buildthon.bank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.levvel.rtp.buildthon.bank.model.WebHook;
import io.levvel.rtp.buildthon.bank.model.WebHookRepository;
import io.levvel.rtp.buildthon.bank.services.NotificationCallbackService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.api.WebhooksApi;
import io.swagger.model.CallbackNotification;
import io.swagger.model.WebHookCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-28T15:52:11.162Z[GMT]")
@Controller
public class WebhooksApiController implements WebhooksApi {

	private static final Logger log = LoggerFactory.getLogger(WebhooksApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	private WebHookRepository repository;

	NotificationCallbackService callbackService;

	@Autowired
	public WebhooksApiController(ObjectMapper objectMapper, HttpServletRequest request,
								 WebHookRepository repository, NotificationCallbackService callbackService) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.repository = repository;
		this.callbackService = callbackService;
	}

	public ResponseEntity<List<WebHook>> getWebInternalHooks() {

		List<WebHook> webhooks = repository.findAll();
		return ResponseEntity.ok(webhooks);
	}

	public ResponseEntity<Void> webhooksPost(@ApiParam(value = "Web Hook to add") @Valid @RequestBody WebHookCallback body) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<List<WebHookCallback>> getWebHooks() {
		return new ResponseEntity<List<WebHookCallback>>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Web hook callback received")})
	@RequestMapping(value = "/webHookCallback",
			consumes = {"application/json"},
			method = RequestMethod.POST)
	public ResponseEntity<String> webHookCallBack(@RequestBody CallbackNotification body) {
		log.info("Callback notification received: {}", body);
		callbackService.addReceivedCallback(body);

		return ResponseEntity.ok("{ \"status\" : \"Callback received\" }");
	}
}
