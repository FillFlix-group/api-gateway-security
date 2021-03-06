package com.lms.apigateway.user;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCodeTokenService {
	// @formatter:off

    public static final String REST_SERVICE_URI = "http://localhost:8080/";
    
    public static final String AUTH_SERVER_URI = "http://localhost:8080/oauth/token";
    
    public static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=lms&password=123";
    
    public static final String QPM_ACCESS_TOKEN = "?access_token=";

 

	public OAuth2Token getToken() {
//		RestTemplate rest = new RestTemplate();
		return sendTokenRequest();
	}

	/*
	 * Send a POST request [on /oauth/token] to get an access-token, which will then
	 * be send with each request.
	 */
	@SuppressWarnings({ "unchecked" })
	private static OAuth2Token sendTokenRequest() {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response =  restTemplate.exchange(AUTH_SERVER_URI + QPM_PASSWORD_GRANT,HttpMethod.POST, 
				request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		OAuth2Token tokenInfo = null;

		if (map != null) {
			tokenInfo = new OAuth2Token();
			tokenInfo.setAccessToken((String) map.get("access_token"));
			tokenInfo.setTokenType((String) map.get("token_type"));
			tokenInfo.setRefreshToken((String) map.get("refresh_token"));
//			tokenInfo.setExpiresIn((String) map.get("expires_in"));

			System.out.println(tokenInfo + "***************");
			// System.out.println("access_token ="+map.get("access_token")+",
			// token_type="+map.get("token_type")+",
			// refresh_token="+map.get("refresh_token")
			// +", expires_in="+map.get("expires_in")+", scope="+map.get("scope"));;
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
