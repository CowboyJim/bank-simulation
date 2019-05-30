package io.levvel.rtp.buildthon.bank.config;


import io.levvel.rtp.buildthon.bank.services.AuthenticationCredentialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Create the RestTemplate with oAuth2 security interceptor
 * <p>
 * <p>
 * Levvel, LLC
 *
 * @author jboone
 */
@Configuration
public class RestTemplateConfig {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);



/*
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
*/


	@Bean
	public RestTemplate restTemplate(AuthenticationCredentialsService credentialsService) {

		RestTemplate template =  new RestTemplateBuilder().interceptors((request, body, execution) -> {

			String bearerToken = credentialsService.getBearerToken();

			if (bearerToken != null) {

				logger.debug("oAuth token found. Value = {} ", bearerToken);

				request.getHeaders().add(HttpHeaders.AUTHORIZATION,
						"Bearer " + bearerToken);
			} else {
				logger.debug("oAuth token NOT found.");
			}

			return execution.execute(request, body);

		}).build();

		template.setRequestFactory(getClientHttpRequestFactory());
		return template;
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 5000;
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
				= new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
	}


/*	@Bean
	public RestTemplate restTemplate(OAuth2AuthorizedClientService clientService) {

		return new RestTemplateBuilder().interceptors((request, body, execution) -> {

			OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(
					SecurityContextHolder.getContext().getAuthentication());

			if (token != null) {

				logger.debug("oAuth token found. Registration ID = {} ", token.getAuthorizedClientRegistrationId());
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
						token.getAuthorizedClientRegistrationId(), token.getName());

				request.getHeaders().add(HttpHeaders.AUTHORIZATION,
						"Bearer " + client.getAccessToken().getTokenValue());
			} else {
				logger.debug("oAuth token NOT found.");
			}

			return execution.execute(request, body);

		}).build();
	}*/


/*	@Bean
	public HttpMessageConverters additionalConverters() {
		FormHttpMessageConverter converter = new FormHttpMessageConverter();
		MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8"));
		converter.setSupportedMediaTypes(Arrays.asList(mediaType));
		return converter;
	}*/

}