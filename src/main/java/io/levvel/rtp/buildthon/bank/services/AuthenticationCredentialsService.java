package io.levvel.rtp.buildthon.bank.services;


import io.levvel.rtp.buildthon.bank.model.dto.TokenRequest;
import io.levvel.rtp.buildthon.bank.model.dto.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AuthenticationCredentialsService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationCredentialsService.class);

	@Value("${apis.token.url}")
	String oAuthUrl;

	@Value("${username}")
	String username;

	@Value("${password}")
	String password;

	String bearerToken;
	String refreshToken;

	int expiresIn;
	LocalDateTime lastUpdated;
	RestTemplate restTemplate;

	@Autowired
	Environment environment;

	public AuthenticationCredentialsService() {
		restTemplate = new RestTemplate();
	}

	public String getBearerToken() {

		if (shouldUpdate()) {
			updateTokens();
		}
		return bearerToken;
	}

	private boolean shouldUpdate() {
		if (lastUpdated == null) {
			return true;
		}
		LocalDateTime now = LocalDateTime.now();
		Duration durationSinceUpdated = Duration.between(lastUpdated, now);
		long difference = expiresIn - durationSinceUpdated.toMillis() / 1000;
		return difference < 0;
	}

	public void updateTokens() {

		for (final String profileName : environment.getActiveProfiles()) {
			if (profileName.equalsIgnoreCase("test")) {
				logger.info("--- Bypassing security server setup ---");
				return;
			}
		}

		logger.info("Updating authentication credentials");
		TokenResponse response = this.getOAuthToken(getTokenRequest());
		bearerToken = response.getAccessToken();
		refreshToken = response.getRefreshToken();

		lastUpdated = LocalDateTime.now();
	}

	/**
	 * Operation expects "application/x-www-form-urlencoded" media type which needs a
	 * MultiValueMap<String, String> to marshall
	 *
	 * @param request
	 * @return
	 */
	public TokenResponse getOAuthToken(TokenRequest request) {

		TokenResponse resp = null;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> requestMap = request.getAsMap();

			// The authorization is hard coded since Oracle didn't give us the basic auth credentials, but did
			// give us this string in their Postman sample
			headers.set(HttpHeaders.AUTHORIZATION, "Basic MmQ3OWU5MzllMDQyYXBpYWNjZXNzOGU1ZmFiNDM2ZmI1NTgxOndlbGNvbWUx");

			HttpEntity<Map> requestEntity = new HttpEntity<>(requestMap, headers);
			logger.debug("getOAuthToken request entity: {}", requestEntity.toString());

			ResponseEntity<TokenResponse> response = this.restTemplate.exchange(oAuthUrl, HttpMethod.POST,
					requestEntity, TokenResponse.class);

			resp = response.getBody();
			expiresIn = resp.getExpires_in() - 20;

			logger.info("getOAuthToken HTTP operationStatus: {}", response.getStatusCode());
			logger.debug("getOAuthToken result: {}", resp);

		} catch (Exception ex) {
			logger.error("Authentication Exception occurred", ex);
			throw ex;
		}
		return resp;
	}

	protected TokenRequest getTokenRequest() {
		TokenRequest request = new TokenRequest();
		request.setUsername(username);
		request.setPassword(password);
		return request;
	}
}
