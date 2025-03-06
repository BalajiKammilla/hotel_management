package com.example.hotel_management_project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_management_project.entity.CustomerLogsEntity;
import com.example.hotel_management_project.service.CustomerLogsService;

@RestController
@RequestMapping("/logs")
public class CustomerLogsResource {

	@Autowired
	private CustomerLogsService customerLogsService;
	
	
	@GetMapping
	public List<CustomerLogsEntity> getAllLogs() {
		return customerLogsService.getAllCustomerLogs();
	}
	
//	@GetMapping("/{customerID}")
//	public List<CustomerLogsEntity> getLogsByCustomerID(@PathVariable String customerID){
//		return customerLogsService.findCustomerLogsByCustomerID(customerID);
//	}
	
}
