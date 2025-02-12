package com.example.hotel_management_project.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.hotel_management_project.entity.CustomerDetailsEntity;

public class UserPrincipal implements UserDetails {
	
	private CustomerDetailsEntity customer;
	
	public UserPrincipal(CustomerDetailsEntity customer) {
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER")); //to define the roles or authorities 
	}

	@Override
	public String getPassword() {
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		return customer.getCustomerName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
	    return true; // Assuming the account is always active
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true; // Assuming the account is never locked
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true; // Assuming credentials never expire
	}

	@Override
	public boolean isEnabled() {
	    return true; // Assuming the account is enabled
	}

}
