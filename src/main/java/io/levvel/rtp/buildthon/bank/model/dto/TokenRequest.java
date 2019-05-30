package io.levvel.rtp.buildthon.bank.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenRequest {

	String grant_type = "PASSWORD";
	String username;
	String password;

	public MultiValueMap<String, String> getAsMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", grant_type);
		map.add("username", username);
		map.add("password", password);

		return map;
	}
}
