package com.lms.apigateway.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.lms.apigateway.security.ClientUserDetails;
import com.lms.apigateway.user.User;

@Service
public class OAuth2ClientTokenSevices implements ClientTokenServices {
	// @formatter:off
	//
	// @Autowired
	// private UserRepository users;

	@Autowired
	OAuth2RestTemplate restTemplate;

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
		User clientUser = getClientUser(authentication);

		String accessToken = clientUser.getAccessToken();
		// Calendar expirationDate = clientUser.getAccessTokenValidity();

		if (accessToken == null)
			return null;

		DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
		// oAuth2AccessToken.setExpiration(expirationDate.getTime());

		return oAuth2AccessToken;
	}

	@Override
	public void saveAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication,
			OAuth2AccessToken accessToken) {
		// Calendar expirationDate = Calendar.getInstance();
		// expirationDate.setTime(accessToken.getExpiration());

		User clientUser = getClientUser(authentication);

		clientUser.setAccessToken(accessToken.getValue());
		// clientUser.setAccessTokenValidity(expirationDate);

		// users.save(clientUser);
		String endpoint="http://localhost:6081/users/"+clientUser.getId();
//		restTemplate.postForObject("http://localhost:6081/users/", clientUser, User.class);
		
		restTemplate.put(endpoint, clientUser);
		
	}

	@Override
	public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
		User clientUser = getClientUser(authentication);

		clientUser.setAccessToken(null);
		clientUser.setRefreshToken(null);
		// clientUser.setAccessTokenValidity(null);

//		users.save(clientUser);
		restTemplate.postForObject("http://localhost:6081/users/", clientUser, User.class);
		
	}

	private User getClientUser(Authentication authentication) {
		ClientUserDetails loggedUser = (ClientUserDetails) authentication.getPrincipal();
		Long userId = loggedUser.getClientUser().getId();
//		User clientUser = users.findOne(userId);
		String endpoint="http://localhost:6081/users/"+ userId ;
		User clientUser = restTemplate.getForObject(endpoint, User.class);
		return clientUser;
	}

	// @formatter:on
}
