package com.example.hotel_management_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.repositoryPl.CustomerRepository;

@Service
public class myUserDetails implements UserDetailsService{

	@Autowired
	private CustomerRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomerDetailsEntity customer = repo.findByCustomerName(username);
		if(customer == null) {
			System.out.println("USER NOT FOUND");
			throw new UsernameNotFoundException("User Not found");
		}
		return new UserPrincipal(customer);
	}

}
