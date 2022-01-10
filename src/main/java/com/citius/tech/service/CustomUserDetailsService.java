package com.citius.tech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.citius.tech.bean.CustomUserDetails;
import com.citius.tech.bean.HospitalUserRegistration;
import com.citius.tech.dao.CustomUserDetailsDao;

@Service
public class CustomUserDetailsService implements UserDetailsService  {
	
	@Autowired
	CustomUserDetailsDao cusUserDetailsDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		HospitalUserRegistration userDetails = cusUserDetailsDao.findByUsername(username);
		if(userDetails != null && userDetails.getId() != null)
			return new CustomUserDetails(userDetails);
		else
			throw new UsernameNotFoundException("Username doesn't exits");
	}

	
}
