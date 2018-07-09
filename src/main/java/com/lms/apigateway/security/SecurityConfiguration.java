package com.lms.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
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
		// .antMatchers("/api/v1/users-service/users/**").permitAll()
		 .antMatchers("/api/v1/users-service/users/**").hasAuthority("CHATTING").anyRequest().authenticated()
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

//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}


}
