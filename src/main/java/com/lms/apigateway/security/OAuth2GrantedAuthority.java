package com.lms.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import com.lms.apigateway.user.Permission;
import com.lms.apigateway.user.Role;
import com.lms.apigateway.user.User;


public class OAuth2GrantedAuthority implements GrantedAuthority {

	// TODO permission from user management
	private static final long serialVersionUID = 1L;

	protected Permission permission;
	protected Role role;
	
	@Autowired
	protected OAuth2RestTemplate restUser;

	public OAuth2GrantedAuthority(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String getAuthority() {
		/*
		 * Returns: a representation of the granted authority
		 */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ClientUserDetails loggedinUser = (ClientUserDetails) authentication.getPrincipal();
		User user = restUser.getForObject("http://localhost:6081/users/" + loggedinUser.getUsername(), User.class);
		role = user.getRole();
		return role.getName();
	}

}
