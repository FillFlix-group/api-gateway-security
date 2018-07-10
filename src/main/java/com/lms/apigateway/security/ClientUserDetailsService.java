package com.lms.apigateway.security;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.lms.apigateway.user.User;

@Service
public class ClientUserDetailsService implements UserDetailsService {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public ClientUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Optional<User> optionalUser = users.findByUsername(username);
		RestTemplate restTemplate = new RestTemplate();
		URI userServiceUri =serviceUrl();
		String endpoint = userServiceUri + "/users/getUser/" + username;
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

	public URI serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("USERS-SERVICE");
		if (list != null && list.size() > 0) {
			return list.get(0).getUri();
		}
		return null;
	}
}