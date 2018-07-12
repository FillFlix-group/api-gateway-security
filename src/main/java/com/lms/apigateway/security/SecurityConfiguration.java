package com.lms.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lms.apigateway.constants.UrlPatterns;
import com.lms.apigateway.oauth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan("com.lms.apigateway.oauth")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements UrlPatterns{

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		 http
		 .authorizeRequests()
		 .antMatchers("/login/**","/oauth/token").permitAll().and()
		 .authorizeRequests()
		 .antMatchers(HttpMethod.POST,UrlPatterns.USERS_SERVICE+UrlPatterns.ADD_USER).hasAnyAuthority("ADD_DRIVER","ADD_PARTNER_BACKOFFICE_USER","ADD_USER")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.USERS_SERVICE+UrlPatterns.EDIT_USER).hasAnyAuthority("EDIT_DRIVER","EDIT_PARTNER_BACKOFFICE_USER","EDIT_USER")
		 .antMatchers(HttpMethod.DELETE,UrlPatterns.USERS_SERVICE+UrlPatterns.DELETE_USER).hasAnyAuthority("DELETE_DRIVER","DELETE_PARTNER_BACKOFFICE_USER","DELETE_USER")
		 .antMatchers(HttpMethod.POST,UrlPatterns.USERS_SERVICE+UrlPatterns.ADD_ROLE).hasAnyAuthority("ADD_ROLE")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.USERS_SERVICE+UrlPatterns.EDIT_ROLE).hasAnyAuthority("EDIT_ROLE")
		 .antMatchers(HttpMethod.DELETE,UrlPatterns.USERS_SERVICE+UrlPatterns.DELETE_ROLE).hasAnyAuthority("DELETE_ROLE")
		 
		 .antMatchers(HttpMethod.POST,UrlPatterns.FREIGHTS_SERVICE+UrlPatterns.ADD_FREIGHT).hasAuthority("ADD_FREIGHT")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.FREIGHTS_SERVICE+UrlPatterns.EDIT_FREIGHT).hasAnyAuthority("EDIT_FREIGHT")
		 .antMatchers(HttpMethod.PATCH,UrlPatterns.FREIGHTS_SERVICE+UrlPatterns.CHANGE_FREIGHT_PROPERTY).hasAnyAuthority("EDIT_FREIGHT")
		 
		 .antMatchers(HttpMethod.POST,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.ADD_VEHICLE).hasAnyAuthority("ADD_VEHICLE")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.EDIT_VEHICLE).hasAnyAuthority("EDIT_VEHICLE")
		 .antMatchers(HttpMethod.PATCH,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.CHANGE_VEHICLE_PROPERTY).hasAnyAuthority("EDIT_VEHICLE", "ARCHIVE_VEHICLE", "UNARCHIVE_VEHICLE")
		 .antMatchers(HttpMethod.DELETE,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.DELETE_VEHICLE).hasAnyAuthority("DELETE_VEHICLE")

		 .antMatchers(HttpMethod.POST,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.ADD_MANUAL_OFFER).hasAnyAuthority("ADD_MANUAL_OFFER")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.EDIT_MANUAL_OFFER).hasAnyAuthority("EDIT_MANUAL_OFFER")
		 .antMatchers(HttpMethod.PATCH,UrlPatterns.VEHICLES_SERVICE+UrlPatterns.CHANGE_MANUAL_OFFER_PROPERTY).hasAnyAuthority("EDIT_MANUAL_OFFER")

		 .antMatchers(HttpMethod.GET,UrlPatterns.REFERENCE_DATA_SERVICE+UrlPatterns.GET_LOOKUPS).hasAnyAuthority("CONFIGURE_LIST_OF_VALUES")

		 .antMatchers(HttpMethod.GET,UrlPatterns.SUBSCRIPTIONS_SERVICE+UrlPatterns.GET_SUBSCRIBER_BY_ID).hasAnyAuthority("SUBSCRIPTION","APPROVE_SUBSCRIPTION")
		 .antMatchers(HttpMethod.POST,UrlPatterns.SUBSCRIPTIONS_SERVICE+UrlPatterns.ADD_SUBSCRIBER).hasAnyAuthority("SUBSCRIPTION")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.SUBSCRIPTIONS_SERVICE+UrlPatterns.EDIT_SUBSCRIBER).hasAnyAuthority("SUBSCRIPTION")
		 .antMatchers(HttpMethod.PATCH,UrlPatterns.SUBSCRIPTIONS_SERVICE+UrlPatterns.CHANGE_SUBSCRIBER_PROPERTY).hasAnyAuthority("APPROVE_SUBSCRIPTION")
		 .antMatchers(HttpMethod.GET,UrlPatterns.SUBSCRIPTIONS_SERVICE+UrlPatterns.GET_ALL_COMPANY_NAMES).permitAll()
		 .antMatchers(HttpMethod.POST,UrlPatterns.SUBSCRIPTIONS_SERVICE+UrlPatterns.SUBSCRIBER_SEARCH).hasAnyAuthority("PARTNER_SEARCH")

		 .antMatchers(HttpMethod.GET,UrlPatterns.SETTINGS_SERVICE+UrlPatterns.GET_SETTINGS).hasAnyAuthority("EDIT_SETTINGS")
		 .antMatchers(HttpMethod.PUT,UrlPatterns.SETTINGS_SERVICE+UrlPatterns.EDIT_SETTINGS).hasAnyAuthority("EDIT_SETTINGS")
		 .antMatchers(HttpMethod.GET,UrlPatterns.SETTINGS_SERVICE+UrlPatterns.GET_START_SETTINGS).hasAnyAuthority("EDIT_SETTINGS")
		 
		 .antMatchers(HttpMethod.POST,UrlPatterns.DOCUMENTS_SERVICE+UrlPatterns.UPLOAD_DOCUMENT).hasAnyAuthority("SUBSCRIPTION")
		 .antMatchers(HttpMethod.GET,UrlPatterns.DOCUMENTS_SERVICE+UrlPatterns.DOWNLOAD_DOCUMENT).permitAll()
		 .antMatchers(HttpMethod.GET,UrlPatterns.DOCUMENTS_SERVICE+UrlPatterns.GET_FILES_NAMES).permitAll()
		 
		 .antMatchers(HttpMethod.GET,UrlPatterns.FREIGHTS_SEARCH_SERVICE+UrlPatterns.GET_FREIGHT_BY_ID).hasAnyAuthority("FREIGHT_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.FREIGHTS_SEARCH_SERVICE+UrlPatterns.GET_SUBSCRIBER_FREIGHTS).hasAnyAuthority("FREIGHT_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.FREIGHTS_SEARCH_SERVICE+UrlPatterns.GET_USER_FREIGHTS).hasAnyAuthority("FREIGHT_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.FREIGHTS_SEARCH_SERVICE+UrlPatterns.FREIGHT_SEARCH).hasAnyAuthority("FREIGHT_SEARCH")
		 
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.GET_VEHICLE_BY_ID).hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.GET_SUBSCRIBER_VEHICLES).hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.GET_USER_VEHICLES).hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.VEHICLE_SEARCH).hasAnyAuthority("VEHICLE_SEARCH")
		 
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.GET_SUBSCRIBER_MANUAL_OFFERS).hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.GET_USER_MANUAL_OFFERS).hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,UrlPatterns.VEHICLES_SEARCH_SERVICE+UrlPatterns.GET_MANUAL_OFFER_BY_ID).hasAnyAuthority("VEHICLE_SEARCH")

		 .anyRequest().authenticated()
		 .and().csrf().disable();

		// @formatter:on
	}
}
