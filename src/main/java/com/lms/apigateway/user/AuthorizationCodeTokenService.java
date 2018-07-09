package com.lms.apigateway.user;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCodeTokenService {

	@Autowired
	private DiscoveryClient discoveryClient;

	// protected String REST_SERVICE_URI =
	// eurekaClient.getNextServerFromEureka("AUTHORIZATION-SERVER", false)
	// .getHomePageUrl();

	// protected String AUTH_SERVER_URI = REST_SERVICE_URI + "oauth/token";

	protected final String QPM_PASSWORD_GRANT = "?grant_type=password";

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
		SecurityContext sc = SecurityContextHolder.getContext();
		String loggedInUserEmail = (String) sc.getAuthentication().getPrincipal();
		String loggedInUserPassword = (String) sc.getAuthentication().getCredentials();
		URI REST_SERVICE_URI = serviceUrl();
		ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI + "/oauth/token" + QPM_PASSWORD_GRANT +"&username="+loggedInUserEmail+"&password="+loggedInUserPassword,
				HttpMethod.POST, request, Object.class);
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

	public URI serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("AUTHORIZATION-SERVER");
		if (list != null && list.size() > 0) {
			return list.get(0).getUri();
		}
		return null;
	}
}
