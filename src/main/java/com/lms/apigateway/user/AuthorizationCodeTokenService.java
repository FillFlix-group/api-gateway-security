package com.lms.apigateway.user;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

@Service
public class AuthorizationCodeTokenService {
	// @formatter:off

	@Autowired
	private EurekaClient eurekaClient;

	// protected String REST_SERVICE_URI =
	// eurekaClient.getNextServerFromEureka("AUTHORIZATION-SERVER", false)
	// .getHomePageUrl();

	// protected String AUTH_SERVER_URI = REST_SERVICE_URI + "oauth/token";

	protected final String QPM_PASSWORD_GRANT = "?grant_type=password&username=lms&password=123";

	protected final String QPM_ACCESS_TOKEN = "?access_token=";

	public OAuth2Token getToken() {
		// RestTemplate rest = new RestTemplate();
		return sendTokenRequest();
	}

	/*
	 * Send a POST request [on /oauth/token] to get an access-token, which will then
	 * be send with each request.
	 */
	@SuppressWarnings({ "unchecked" })
	private OAuth2Token sendTokenRequest() {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		String REST_SERVICE_URI = eurekaClient.getNextServerFromEureka("AUTHORIZATION-SERVER", false).getHomePageUrl();
		ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI+"oauth/token" + QPM_PASSWORD_GRANT, HttpMethod.POST,
				request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		OAuth2Token tokenInfo = null;

		if (map != null) {
			tokenInfo = new OAuth2Token();
			tokenInfo.setAccessToken((String) map.get("access_token"));
			tokenInfo.setTokenType((String) map.get("token_type"));
			// tokenInfo.setRefreshToken((String) map.get("refresh_token"));
			tokenInfo.setExpiresIn((int) map.get("expires_in"));
			tokenInfo.setScope((String) map.get("scope"));

		} else {
			System.out.println("No user exist----------");

		}
		return tokenInfo;
	}

	private static HttpHeaders getHeadersWithClientCredentials() {
		String plainClientCredentials = "clientapp:123456";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		return headers;
	}

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	// @formatter:on
}
