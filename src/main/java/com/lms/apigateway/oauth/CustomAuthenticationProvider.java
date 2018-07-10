package com.lms.apigateway.oauth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
/*	@Autowired
	ClientUserDetails clientUserDetails;

	public CustomAuthenticationProvider(ClientUserDetails clientUserDetails){
		this.clientUserDetails = clientUserDetails;
	}
*/	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password, authentication.getAuthorities());
		return auth;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
