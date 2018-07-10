package com.lms.apigateway.user;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.lms.apigateway.oauth.CustomAuthenticationProvider;
import com.lms.apigateway.security.ClientUserDetails;
import com.lms.apigateway.security.ClientUserDetailsService;

@Controller
public class APiController {
	// @formatter:off

	// private AccessTokenRequest accessTokenRequest = new
	// DefaultAccessTokenRequest();

	@Autowired
	private ClientUserDetailsService service;
	@Autowired
	private AuthorizationCodeTokenService tokenService;

	@Autowired
	CustomAuthenticationProvider authenticationManager;

	@Autowired
	AuthenticationProvider provider;

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * TODO set refresh token with the right value in login response header
	 * 
	 * @param username
	 * @param password
	 * @param response
	 * @return
	 */

	@PostMapping("/login")
	@ResponseBody
	public User login(@RequestBody User user, HttpServletResponse response) {

		ClientUserDetails clientUserDetails = service.loadUserByUsername(user.getUsername());

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
				clientUserDetails.getAuthorities());

		if (!clientUserDetails.getPassword().equals(user.getPassword())) {
			throw new BadClientCredentialsException();
		}

		Authentication auth = authenticationManager.authenticate(authReq);

		System.out.println("******User Authoritie ********" + auth.getAuthorities());

		User clientUser = clientUserDetails.getClientUser();

		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);

			// TO DO Request an access token using password credienal
			/*
			 * grant_type= password user name --> logged in user user password --> client
			 * crediatial
			 */
			OAuth2Token token = tokenService.getToken();
			clientUser.setAccessToken(token.getAccessToken());
			// users.save(clientUser);

			URI UriOfUserservice = serviceUrl();
			String endpoint = UriOfUserservice + "/users/" + clientUser.getId();
			RestTemplate rest = new RestTemplate();
			rest.put(endpoint, clientUser);
			response.addIntHeader("X-User-ID", clientUser.getId().intValue());
			response.addHeader("X-Access-Token", token.getAccessToken());
			response.addHeader("X-Token-Type", token.getTokenType());
			response.addIntHeader("X-Expires-In", token.getExpiresIn());
			// response.addHeader("X-Refresh-Token", clientUser.getRefreshToken());
			response.addHeader("X-Scope", token.getScope());
			// response.addHeader("X-State", null);
		return clientUser;
		// return mv;
	}

	public URI serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("USERS-SERVICE");
		if (list != null && list.size() > 0) {
			return list.get(0).getUri();
		}
		return null;
	}
}
