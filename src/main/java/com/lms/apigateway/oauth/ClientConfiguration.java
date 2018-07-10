package com.lms.apigateway.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class ClientConfiguration {

	@Autowired
	public ClientTokenServices clientTokenServices;

	@Bean
	public OAuth2ProtectedResourceDetails resource() {
		// @formatter:off
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();

		resourceDetails.setId("oauth2server");
		resourceDetails.setTokenName("oauth_token");
		resourceDetails.setClientId("clientapp");
		resourceDetails.setClientSecret("123456");
		resourceDetails.setAccessTokenUri("http://localhost:9005/oauth/token");
		resourceDetails.setScope(Arrays.asList("read_profile"));
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		// @formatter:on

		return resourceDetails;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public OAuth2RestTemplate restTemplate() {

		OAuth2ProtectedResourceDetails resourceDetails = resource();

		OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails,
				new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));

		AccessTokenProviderChain provider = new AccessTokenProviderChain(
				Arrays.asList(new ResourceOwnerPasswordAccessTokenProvider()));

		provider.setClientTokenServices(clientTokenServices);
		template.setAccessTokenProvider(provider);

		return template;
	}

}
