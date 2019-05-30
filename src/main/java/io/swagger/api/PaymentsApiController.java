package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Payments;
import io.swagger.model.SendPaymentReponse;
import io.swagger.model.SendPaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-27T17:13:16.574Z[GMT]")
@Controller
public class PaymentsApiController implements PaymentsApi {

    private static final Logger log = LoggerFactory.getLogger(PaymentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PaymentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Payments>> getPayments(@ApiParam(value = "Type of payment, real time or ach" ,required=true) @RequestHeader(value="type", required=true) String type) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Payments>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SendPaymentReponse> sendPayment(@ApiParam(value = "Inventory item to add"  )  @Valid @RequestBody SendPaymentRequest body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<SendPaymentReponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
