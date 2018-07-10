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

import com.lms.apigateway.oauth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan("com.lms.apigateway.oauth")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
		 .antMatchers(HttpMethod.POST,"/api/v1/users-service/users").hasAnyAuthority("ADD_DRIVER","ADD_PARTNER_BACKOFFICE_USER","ADD_USER")
		 .antMatchers(HttpMethod.PUT,"/api/v1/users-service/users/{id}").hasAnyAuthority("EDIT_DRIVER","EDIT_PARTNER_BACKOFFICE_USER","EDIT_USER")
		 .antMatchers(HttpMethod.DELETE,"/api/v1/users-service/users/{id}").hasAnyAuthority("DELETE_DRIVER","DELETE_PARTNER_BACKOFFICE_USER","DELETE_USER")
		 .antMatchers(HttpMethod.POST,"/api/v1/users-service/roles").hasAnyAuthority("ADD_ROLE")
		 .antMatchers(HttpMethod.PUT,"/api/v1/users-service/roles/{id}").hasAnyAuthority("EDIT_ROLE")
		 .antMatchers(HttpMethod.DELETE,"/api/v1/users-service/roles/{id}").hasAnyAuthority("DELETE_ROLE")
		 
		 .antMatchers(HttpMethod.POST,"/api/v1/freights-service/freights").hasAuthority("ADD_FREIGHT")
		 .antMatchers(HttpMethod.PUT,"/api/v1/freights-service/freights/{id}").hasAnyAuthority("EDIT_FREIGHT")
		 .antMatchers(HttpMethod.PATCH,"/api/v1/freights-service/freights/{id}").hasAnyAuthority("EDIT_FREIGHT")
		 
		 .antMatchers(HttpMethod.POST,"/api/v1/vehicles-service/vehicles").hasAnyAuthority("ADD_VEHICLE")
		 .antMatchers(HttpMethod.PUT,"/api/v1/vehicles-service/vehicles/{id}").hasAnyAuthority("EDIT_VEHICLE")
		 .antMatchers(HttpMethod.PATCH,"/api/v1/vehicles-service/vehicles/{id}").hasAnyAuthority("EDIT_VEHICLE", "ARCHIVE_VEHICLE", "UNARCHIVE_VEHICLE")
		 .antMatchers(HttpMethod.DELETE,"/api/v1/vehicles-service/vehicles/{id}").hasAnyAuthority("DELETE_VEHICLE")

		 .antMatchers(HttpMethod.POST,"/api/v1/vehicles-service/manualOffers").hasAnyAuthority("ADD_MANUAL_OFFER")
		 .antMatchers(HttpMethod.PUT,"/api/v1/vehicles-service/manualOffers/{id}").hasAnyAuthority("EDIT_MANUAL_OFFER")
		 .antMatchers(HttpMethod.PATCH,"/api/v1/vehicles-service/manualOffers/{id}").hasAnyAuthority("EDIT_MANUAL_OFFER")

		 .antMatchers(HttpMethod.GET,"/api/v1/reference-data-service/lookups").hasAnyAuthority("CONFIGURE_LIST_OF_VALUES")

		 .antMatchers(HttpMethod.GET,"/api/v1/subscriptions-service/subscriptions/{id}").hasAnyAuthority("SUBSCRIPTION","APPROVE_SUBSCRIPTION")
		 .antMatchers(HttpMethod.POST,"/api/v1/subscriptions-service/subscriptions").hasAnyAuthority("SUBSCRIPTION")
		 .antMatchers(HttpMethod.PUT,"/api/v1/subscriptions-service/subscriptions/{id}").hasAnyAuthority("SUBSCRIPTION")
		 .antMatchers(HttpMethod.PATCH,"/api/v1/subscriptions-service/subscriptions/{id}").hasAnyAuthority("APPROVE_SUBSCRIPTION")
		 .antMatchers(HttpMethod.GET,"/api/v1/subscriptions-service/subscriptions/companyNames").permitAll()
		 .antMatchers(HttpMethod.POST,"/api/v1/subscriptions-service/subscriptions/search").hasAnyAuthority("PARTNER_SEARCH")

		 .antMatchers(HttpMethod.GET,"/api/v1/settings-service/settings/{id}").hasAnyAuthority("EDIT_SETTINGS")
		 .antMatchers(HttpMethod.PUT,"/api/v1/settings-service/settings/{id}").hasAnyAuthority("EDIT_SETTINGS")
		 .antMatchers(HttpMethod.GET,"/api/v1/settings-service/startsettings/{id}").hasAnyAuthority("EDIT_SETTINGS")
		 
		 .antMatchers(HttpMethod.POST,"/api/v1/documents-service/documents/upload/{prefix}/{documentType}").hasAnyAuthority("SUBSCRIPTION")
		 .antMatchers(HttpMethod.GET,"/api/v1/documents-service/documents/download/{prefix}/{documentType}").permitAll()
		 .antMatchers(HttpMethod.GET,"/api/v1/documents-service/documents/types/{prefix}").permitAll()
		 
		 .antMatchers(HttpMethod.GET,"/api/v1/freights-search-service/freights/{freightId}").hasAnyAuthority("FREIGHT_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/freights-search-service/freights/subscription/{subscriberId}").hasAnyAuthority("FREIGHT_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/freights-search-service/freights/user/{userId}").hasAnyAuthority("FREIGHT_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/freights-search-service/freights/search").hasAnyAuthority("FREIGHT_SEARCH")
		 
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/vehicles/{vehicleId}").hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/vehicles/subscription/{subscriberId}").hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/vehicles/user/{userId}").hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/vehiclesQuery/search").hasAnyAuthority("VEHICLE_SEARCH")
		 
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/manualOffers/subscription/{subscriberId}").hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/manualOffers/user/{userId}").hasAnyAuthority("VEHICLE_SEARCH")
		 .antMatchers(HttpMethod.GET,"/api/v1/vehicles-search-service/manualOffers/{manualOfferId}").hasAnyAuthority("VEHICLE_SEARCH")

		 .anyRequest().authenticated()
		 .and().csrf().disable();

	//	http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

		// .logout().permitAll().and()
		// .csrf().disable();

		// http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();

		// http.authorizeRequests().antMatchers(SECURED_USERS_PATTERN).access("hasRole('PARTNER_ADMIN')")

		// .antMatchers(SECURED_FREIGHT_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')")

		// .antMatchers(SECURED_FREIGHT_SEARCH_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')
		// and hasRole('PARTNER_DRIVER') and hasRole('SELF_EMPLOYED_DRIVER')")

		// .antMatchers(SECURED_VEHICLES_PATTERN).access("hasRole('PARTNER_ADMIN') and
		// hasRole('SELF_EMPLOYED_DRIVER') and hasRole('CONTRACTOR')")

		// .antMatchers(SECURED_VEHICLES_ARCHIVE_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')
		// and hasRole('SELF_EMPLOYED_DRIVER') and hasRole('CONTRACTOR')")

		// .antMatchers(SECURED_VEHICLE_SEARCH_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')")

		// .antMatchers(SECURED_MANUALOFFERS_POST_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')
		// and hasRole('SELF_EMPLOYED_DRIVER')")

		// .antMatchers(SECURED_MANUALOFFERS_SEARCH_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')
		// and hasRole('SELF_EMPLOYED_DRIVER')")

		// .antMatchers(SECURED_SUBSCRIPTION_PATTERN).access("hasRole('PARTNER_ADMIN')
		// and hasRole('SELF_EMPLOYED_DRIVER') and hasRole('CONTRACTOR')")

		// .antMatchers(SECURED_SETTINGS_PATTERN).access("hasRole('PARTNER_ADMIN') and
		// hasRole('PARTNER_DRIVER') and hasRole('SELF_EMPLOYED_DRIVER') and
		// hasRole('CONTRACTOR')")

		// .antMatchers(SECURED_DOCUMENTS_PATTERN).access("hasRole('SUPER_USER')")

		// .antMatchers(SECURED_USERS_PATTERN).access("hasRole('PARTNER_ADMIN')")

		// .antMatchers(SECURED_USERS_SEARCH_PATTERN).access("hasRole('PARTNER_BACKOFFICE_USER')")

		// .and().authorizeRequests()

		// .antMatchers(UNSECURED_PATTERN).permitAll()

		// .antMatchers(UNSECURED_LOGIN_PATTERN).permitAll()

		// .antMatchers(UNSECURED_REGISTER_PATTERN).permitAll()

		// .anyRequest().authenticated()

		// .and().sessionManagement()

		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

		// .and().csrf();

		// @formatter:on
	}
}
