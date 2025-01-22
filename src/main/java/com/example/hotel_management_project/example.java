package com.example.hotel_management_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class example {
	
	@GetMapping("/hello")
	public String getName() {
		return "hello Spring MVC project";
	}
}
