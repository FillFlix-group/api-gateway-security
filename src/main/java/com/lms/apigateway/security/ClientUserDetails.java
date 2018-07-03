package com.lms.apigateway.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lms.apigateway.user.User;

public class ClientUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private User clientUser;

	public ClientUserDetails(User user) {
		this.clientUser = user;
	}

	public User getClientUser() {
		return clientUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(clientUser.getRole().getName()));
		clientUser.getRole().getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getName()))
				.forEach(authorities::add);

		return authorities;
	}

	@Override
	public String getPassword() {
		return clientUser.getPassword();
	}

	@Override
	public String getUsername() {
		return clientUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
