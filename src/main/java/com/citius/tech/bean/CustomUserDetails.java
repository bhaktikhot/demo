package com.citius.tech.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.citius.tech.commons.Constants;

public class CustomUserDetails implements UserDetails {
	
	private HospitalUserRegistration hospitalUserRegistration;
	
	public  CustomUserDetails(HospitalUserRegistration hospitalUserRegistration) {
		this.hospitalUserRegistration = hospitalUserRegistration;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantAuthorities= new ArrayList<>();
		grantAuthorities.add(new SimpleGrantedAuthority(hospitalUserRegistration.getRole().toString()));
		return grantAuthorities;
	}

	@Override
	public String getPassword() {
		return this.hospitalUserRegistration.getPassword();
	}

	@Override
	public String getUsername() {
		return this.hospitalUserRegistration.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.hospitalUserRegistration.getAccountStatus().equals(Constants.ACTIVE_ACCOUNT_STATUS);
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
