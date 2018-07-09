package com.lms.apigateway.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.lms.apigateway.security.ClientUserDetails;


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

		// use the credentials
		// and authenticate against the third-party system
		
	//	List<GrantedAuthority> grantedAuths = new ArrayList<>();
	//	grantedAuths.add(new SimpleGrantedAuthority("CHATTING"));
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password, authentication.getAuthorities());
//		System.out.println("******************************************"+ clientUserDetails.getAuthorities());
		return auth;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
