package com.citius.tech.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.citius.tech.bean.HospitalUserRegistration;
import com.citius.tech.dao.CustomUserDetailsDao;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private CustomUserDetailsDao cusUserDetailsDao;
	
	/*
	 * @Autowired private UserDetailsService service;
	 */
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("authentication bean "+authentication);
		log.info("authentication principal "+authentication.getPrincipal());
		log.info("authentication getCredentials "+authentication.getCredentials());
		log.info("authentication getDetails "+authentication.getDetails());
		
		HospitalUserRegistration userDetailsDb =cusUserDetailsDao.findByUsername(authentication.getPrincipal().toString());
		log.info("user details from database" + userDetailsDb);
		if(userDetailsDb != null && userDetailsDb.getId() != null)
		{
			if(passwordEncoder.matches(authentication.getCredentials().toString(),  userDetailsDb.getPassword()))
			{
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>(0);
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials().toString(), grantedAuths);
				log.info("auth"+auth);
				return auth;
			}else 
				throw new BadCredentialsException("Invalid Credentails");
		}
		else
			throw new UsernameNotFoundException("Username doesn't exits");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
