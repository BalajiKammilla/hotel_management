package com.example.hotel_management_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.entity.CustomerLogsEntity;
import com.example.hotel_management_project.entity.PaymentDetailsEntity;
import com.example.hotel_management_project.entity.RoomDetailsEntity;
import com.example.hotel_management_project.repositoryPl.CustomerLogRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerLogsService {

	@Autowired
	private CustomerLogRepository customerLogRepository;
    
    public CustomerLogsEntity saveCustomerLogs(CustomerDetailsEntity custDetails, RoomDetailsEntity roomDetails, PaymentDetailsEntity paymentDetails) {
        CustomerLogsEntity log = new CustomerLogsEntity();
        log.setCustomerDetailsEntity(custDetails);
        log.setRoomDetailsEntity(roomDetails);
        log.setPaymentDetailsEntity(paymentDetails);	

        return customerLogRepository.save(log);
    }
	
    @Transactional
	public List<CustomerLogsEntity> getAllCustomerLogs() {
		return customerLogRepository.findAll();
	}

}
