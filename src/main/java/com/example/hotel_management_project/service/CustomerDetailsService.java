package com.example.hotel_management_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_management_project.dto.CustomerDetails;
import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.exception.ValidationException;
import com.example.hotel_management_project.repositoryPl.CustomerRepository;

@Service
public class CustomerDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Optional<CustomerDetailsEntity> getCustomerDetailsById(Long id) {
		
		if(id == null || id <= 0) {
			throw new ValidationException("Invalid Id, ID must be positive number");
		}
		return customerRepository.findById(id);
	}
	
	public List<CustomerDetailsEntity> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	
	public List<CustomerDetailsEntity> getCustomersByName(String customerName) {
		if(customerName == null || customerName.isEmpty()) {
			throw new ValidationException("CustomerName cannot be null or empty");
		}
		
		return customerRepository.getCustomerDetailsByCustomerName(customerName);
	}
	
	
	public CustomerDetailsEntity saveDetails(CustomerDetails customerDetails) {
		
		CustomerDetailsValidations(customerDetails);
		
        CustomerDetailsEntity entity = new CustomerDetailsEntity();
        entity.setCustomerName(customerDetails.getCustomerName());
        entity.setAge(customerDetails.getAge());
        entity.setAddress(customerDetails.getAddress());
        entity.setCountryCode(customerDetails.getCountryCode());
        entity.setIdProof(customerDetails.getIdProof());
        entity.setMobileNumber(customerDetails.getMobileNumber());
     
        return customerRepository.save(entity);
    }

	private void CustomerDetailsValidations(CustomerDetails customerDetails) {
		if(customerDetails.getCustomerName() == null || customerDetails.getCustomerName().isEmpty() && customerDetails.getCustomerName().length() >= 10) {
			throw new ValidationException("Customer Name cannot be null or empty and less than 10 charcters ");
		}
		if(!customerDetails.getCustomerName().matches("[A-Za-z\\s]+")) {
			throw new ValidationException("CustomerName can only contain alphabets and spaces");
		}
		if(customerDetails.getAddress() == null || customerDetails.getAddress().isEmpty()) {
			throw new ValidationException("Address cannot be null or empty");
		}
		if(customerDetails.getAddress().length() > 255) {
			throw new ValidationException("Address cannot exceed 255 charcters");
		}
		if(customerDetails.getAge() <= 0) {
			throw new ValidationException("Age must be greater than 0");
		}
		if(customerDetails.getCountryCode() == null || customerDetails.getCountryCode().isEmpty()) {
			throw new ValidationException("Country code cannot be null or empty");
		}
		if(customerDetails.getIdProof() == null || customerDetails.getIdProof().isEmpty()) {
			throw new ValidationException("IdProof cannot be null or empty");
		}
		if(customerDetails.getMobileNumber().equals("+91") && customerDetails.getMobileNumber() == null || !customerDetails.getMobileNumber().matches("\\d{10}")) {
			throw new ValidationException("Invalid MobileNumber, Indian mobileNumbers should conatain 10 digits");
		}
		if(customerRepository.existsByMobileNumber(customerDetails.getMobileNumber())) {
			throw new ValidationException("Mobile number already exists");
		}
	}
	
	public CustomerDetailsEntity updateDetails(Long id, CustomerDetails custDetails) {
		
	      CustomerDetailsEntity existingEntity = customerRepository.findById(id)
	              .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
	      
	      existingEntity.setCustomerName(custDetails.getCustomerName());
	      existingEntity.setAddress(custDetails.getAddress());
	      existingEntity.setCountryCode(custDetails.getCountryCode());
	      existingEntity.setAge(custDetails.getAge());
	      existingEntity.setIdProof(custDetails.getIdProof());
	      existingEntity.setMobileNumber(custDetails.getMobileNumber());
	      
	      return customerRepository.save(existingEntity);
	}
	
	
	public void deleteCustomer(Long id) {
		if(!customerRepository.existsById(id)) {
			throw new RuntimeException("Customer not found with id"+id);
		}
		customerRepository.deleteById(id);
	}

}
