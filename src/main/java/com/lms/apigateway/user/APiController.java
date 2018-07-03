package com.lms.apigateway.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.lms.apigateway.oauth.CustomAuthenticationProvider;
import com.lms.apigateway.security.ClientUserDetails;
import com.lms.apigateway.security.ClientUserDetailsService;

@Controller
public class APiController {
	// @formatter:off

	@Autowired
	private OAuth2RestTemplate restTemplate;

	// @Autowired
	// private OAuth2ClientTokenSevices oAuth2ClientTokenSevices;

	private AccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();

	@Autowired
	private ClientUserDetailsService service;
	@Autowired
	private AuthorizationCodeTokenService tokenService;

	@Autowired
	CustomAuthenticationProvider authenticationManager;

	@Autowired
	AuthenticationProvider provider;

	@GetMapping("/login")
	@ResponseBody
	public User login(String username, String password) {

		ClientUserDetails clientUserDetails = service.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);

		if (!clientUserDetails.getPassword().equals(password)) {
			throw new BadClientCredentialsException();
		}

		Authentication auth = authenticationManager.authenticate(authReq);

		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);

		System.out.println("******User Authoritie ********" + clientUserDetails.getAuthorities());

		User clientUser = clientUserDetails.getClientUser();

		if (clientUser.getAccessToken() == null) {
			// TO DO Request an access token using password credienal
			/*
			 * grant_type= password user name --> logged in user user password --> client
			 * crediatial
			 */
			OAuth2Token token = tokenService.getToken();
			clientUser.setAccessToken(token.getAccessToken());
			// users.save(clientUser);
			String endpoint = "http://localhost:6081/users/" + clientUser.getId();
			// restTemplate.postForObject("http://localhost:6081/users/", clientUser,
			// User.class);
			RestTemplate rest = new RestTemplate();
			rest.put(endpoint, clientUser);

		}

		return clientUser;
		// return mv;
	}

	private User tryToGetUserProfile() {
		//
		accessTokenRequest.add("username", "lms");
		accessTokenRequest.add("password", "123");

		String endpoint = "http://localhost:8080/api/profile";
		User userProfile;
		try {
			userProfile = restTemplate.getForObject(endpoint, User.class);

		} catch (HttpClientErrorException e) {
			throw new RuntimeException("it was not possible to retrieve user profile");
		}
		return userProfile;

	}

}
