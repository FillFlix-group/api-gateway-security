package com.lms.apigateway.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.lms.apigateway.user.User;

@Service
public class ClientUserDetailsService implements UserDetailsService {

	@Override
	public ClientUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Optional<User> optionalUser = users.findByUsername(username);
		RestTemplate restTemplate = new RestTemplate();
		String endpoint = "http://localhost:6081/users/getUser/" + username;
		try {

			User optionalUser = restTemplate.getForObject(endpoint, User.class);
			if (optionalUser == null) {
				throw new UsernameNotFoundException("invalid username or password");
			}

			return new ClientUserDetails(optionalUser);

		} catch (HttpClientErrorException e) {
			throw new RuntimeException("it was not possible to retrieve user profile");
		}

	}
}