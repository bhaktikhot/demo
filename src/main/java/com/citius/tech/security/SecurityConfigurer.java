package com.citius.tech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.citius.tech.service.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService customUserService;
	
	/*
	 * @Autowired AuthenticationProvider authenticationProvider;
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.authenticationProvider(authenticationProvider); }
	 */

	@Override protected void configure(HttpSecurity http) throws Exception {
	  
	  http.authorizeRequests()
	  .antMatchers("/").authenticated() .and().formLogin(); }
	
	/*
	 * .antMatchers("").hasRole("Admin") .antMatchers("").hasAnyRole("Admin",
	 * "User")
	 */

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new SCryptPasswordEncoder();
	}

}
