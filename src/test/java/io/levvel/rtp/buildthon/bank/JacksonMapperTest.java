package io.levvel.rtp.buildthon.bank;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.levvel.rtp.buildthon.bank.model.dto.RealTimePaymentPatchResponse;
import io.swagger.model.Notifications;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JacksonMapperTest {

	ObjectMapper mapper;

	@Before
	public void init() {
		mapper = new ObjectMapper();
	}


	@Test
	public void testNotificationResultMapper() throws Exception {

		List<Notifications> notifications = mapper.readValue(notificationsJson, List.class);

		System.out.println("--- Notification Result  ---");
		System.out.println(notifications.toString());
	}

	@Test
	public void testRealTimePaymentMapper() throws Exception {

		String rtPaymentRequest = mapper.writerWithDefaultPrettyPrinter().
				writeValueAsString(BankApplicationTests.getRealTimePaymentRequest());
		System.out.println("--- RT Payment Request ---");
		System.out.println(rtPaymentRequest);
	}

	@Test
	public void testPaymentPatchMapper() throws IOException {

		RealTimePaymentPatchResponse response = mapper.readValue(patchResponse, RealTimePaymentPatchResponse.class);

		System.out.println("--- RT Patch response ---");
		System.out.println(response.toString());

		response = mapper.readValue(jsonResponseForPatch, RealTimePaymentPatchResponse.class);
		System.out.println(response.toString());
	}



	// BuildMyString.com generated code. Please enjoy your string responsibly.
	String jsonResponseForPatch = "	" +
			"{" +
			"  \"dictionaryArray\": [" +
			"    {" +
			"      \"nameValuePairDTOArray\": [" +
			"        {" +
			"          \"name\": \"string\"," +
			"          \"genericName\": \"string\"," +
			"          \"value\": \"string\"," +
			"          \"datatype\": \"string\"" +
			"        }" +
			"      ]," +
			"      \"fullyQualifiedClassName\": \"string\"" +
			"    }" +
			"  ]," +
			"  \"refLinks\": [" +
			"    {" +
			"      \"href\": \"string\"," +
			"      \"rel\": \"string\"," +
			"      \"method\": \"string\"" +
			"    }" +
			"  ]," +
			"  \"status\": {" +
			"    \"result\": \"SUCCESSFUL\"," +
			"    \"referenceNumber\": \"string\"," +
			"    \"externalReferenceNumber\": \"string\"," +
			"    \"contextID\": \"string\"," +
			"    \"message\": {" +
			"      \"title\": \"string\"," +
			"      \"detail\": \"string\"," +
			"      \"code\": \"string\"," +
			"      \"validationError\": [" +
			"        {" +
			"          \"objectName\": \"string\"," +
			"          \"attributeName\": \"string\"," +
			"          \"attributeValue\": \"string\"," +
			"          \"errorCode\": \"string\"," +
			"          \"errorMessage\": \"string\"," +
			"          \"methodName\": \"string\"," +
			"          \"applicableAttributes\": \"string\"," +
			"          \"errorMessageParams\": [" +
			"            \"string\"" +
			"          ]" +
			"        }" +
			"      ]," +
			"      \"relatedMessage\": [" +
			"        null" +
			"      ]," +
			"      \"type\": \"INFO\"" +
			"    }," +
			"    \"receiptAvailable\": true," +
			"    \"lastKnownError\": {" +
			"      \"stackTrace\": [" +
			"        {" +
			"          \"methodName\": \"string\"," +
			"          \"fileName\": \"string\"," +
			"          \"lineNumber\": 0," +
			"          \"className\": \"string\"," +
			"          \"nativeMethod\": true" +
			"        }" +
			"      ]," +
			"      \"localizedMessage\": \"string\"," +
			"      \"message\": \"string\"," +
			"      \"suppressed\": [" +
			"        {" +
			"          \"stackTrace\": [" +
			"            {" +
			"              \"methodName\": \"string\"," +
			"              \"fileName\": \"string\"," +
			"              \"lineNumber\": 0," +
			"              \"className\": \"string\"," +
			"              \"nativeMethod\": true" +
			"            }" +
			"          ]," +
			"          \"localizedMessage\": \"string\"," +
			"          \"message\": \"string\"" +
			"        }" +
			"      ]" +
			"    }" +
			"  }," +
			"  \"externalReferenceId\": \"extRefId\"," +
			"  \"uniqueEndToEndTxnReference\": \"end2end\"" +
			"}";


	String patchResponse = "{" +
			"  \"status\": {" +
			"    \"result\": \"SUCCESSFUL\"," +
			"    \"externalReferenceNumber\": \"1914817492800003\"," +
			"    \"contextID\": \"005YVZAgaWMFw005zzo2yW0002v3000BGO,0&#x3a;1\"," +
			"    \"message\": {" +
			"      \"code\": \"0\"," +
			"      \"type\": \"INFO\"" +
			"    }" +
			"  }," +
			"  \"externalReferenceId\": \"1914817492800003\"" +
			"}";

	String notificationsJson =
			"{" +
					"  \"status\": {" +
					"    \"result\": \"SUCCESSFUL\"," +
					"    \"contextID\": \"005YVAAjjBOFw005zzo2yW0002v3000Ao3,0&#x3a;1\"," +
					"    \"message\": {" +
					"      \"code\": \"0\"," +
					"      \"type\": \"INFO\"" +
					"    }" +
					"  }," +
					"  \"alertDTOs\": [" +
					"    {" +
					"      \"creationDate\": \"2019-05-26T07:44:04.000+05:30\"," +
					"      \"version\": 1," +
					"      \"generatedPackageId\": false," +
					"      \"auditSequence\": 1," +
					"      \"messageId\": {" +
					"        \"displayValue\": \"xxxx107\"," +
					"        \"value\": \"0033107\"" +
					"      }," +
					"      \"contentDTO\": []," +
					"      \"messageType\": \"A\"," +
					"      \"subject\": \"RFP Alert\"," +
					"      \"messageBody\": \"Payment accepted by Scott Hall with details -  Instruction_Id &#x3a; 20190524021000018BNBDVSK2114312032 -  E2e_Id &#x3a; WEV4GGJWOD -  Transfer_Ccy &#x3a; USD -  TranferAmt &#x3a; 50 -  Cr_Agent_Mem_Id &#x3a; 021000020\"," +
					"      \"messageUserMappings\": [" +
					"        {" +
					"          \"version\": 1," +
					"          \"generatedPackageId\": false," +
					"          \"auditSequence\": 1," +
					"          \"mapId\": \"0033757\"," +
					"          \"userId\": \"MartinE\"," +
					"          \"deleteStatus\": false," +
					"          \"msgFlag\": \"T\"," +
					"          \"status\": \"U\"" +
					"        }" +
					"      ]" +
					"    }," +
					"    {" +
					"      \"creationDate\": \"2019-05-24T03:18:02.000+05:30\"," +
					"      \"version\": 1," +
					"      \"generatedPackageId\": false," +
					"      \"auditSequence\": 1," +
					"      \"messageId\": {" +
					"        \"displayValue\": \"xxxx062\"," +
					"        \"value\": \"0033062\"" +
					"      }," +
					"      \"contentDTO\": []," +
					"      \"messageType\": \"A\"," +
					"      \"subject\": \"RFP Alert\"," +
					"      \"messageBody\": \"Payment accepted by Scott Hall with details -  Instruction_Id &#x3a; 20190523021000018BNBHPUQ21452623980 -  E2e_Id &#x3a; E7ZEHHDSN8 -  Transfer_Ccy &#x3a; USD -  TranferAmt &#x3a; 1.03 -  Cr_Agent_Mem_Id &#x3a; 021000020\"," +
					"      \"messageUserMappings\": [" +
					"        {" +
					"          \"version\": 1," +
					"          \"generatedPackageId\": false," +
					"          \"auditSequence\": 1," +
					"          \"mapId\": \"0033711\"," +
					"          \"userId\": \"MartinE\"," +
					"          \"deleteStatus\": false," +
					"          \"msgFlag\": \"T\"," +
					"          \"status\": \"U\"" +
					"        }" +
					"      ]" +
					"    }" +
					"  ]" +
					"}";
}