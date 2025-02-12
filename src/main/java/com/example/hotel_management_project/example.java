package com.example.hotel_management_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class example {
	
	@GetMapping("/")
	public String getName(HttpServletRequest request) {
		return "hello Spring MVC project " + request.getSession().getId(); //retrieves SessionId for each and every login
	}
}
