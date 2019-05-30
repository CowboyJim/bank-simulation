/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Payments;
import io.swagger.model.SendPaymentReponse;
import io.swagger.model.SendPaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-27T17:13:16.574Z[GMT]")
@Api(value = "payments", description = "the payments API")
public interface PaymentsApi {

    @ApiOperation(value = "returns payments of specified type", nickname = "getPayments", notes = "Search for all payment of the given type ", response = Payments.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Payments.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/payments",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Payments>> getPayments(@ApiParam(value = "Type of payment, real time or ach" ,required=true) @RequestHeader(value="type", required=true) String type);


    @ApiOperation(value = "Submits a new payment", nickname = "sendPayment", notes = "Sends a payment of the specified type", response = SendPaymentReponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Payment submitted", response = SendPaymentReponse.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/payments",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<SendPaymentReponse> sendPayment(@ApiParam(value = "Inventory item to add"  )  @Valid @RequestBody SendPaymentRequest body);

}