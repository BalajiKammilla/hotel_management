package com.example.hotel_management_project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_management_project.dto.PaymentDetails;
import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.entity.CustomerLogsEntity;
import com.example.hotel_management_project.entity.PaymentDetailsEntity;
import com.example.hotel_management_project.entity.RoomDetailsEntity;
import com.example.hotel_management_project.repositoryPl.CustomerLogRepository;
import com.example.hotel_management_project.repositoryPl.CustomerRepository;
import com.example.hotel_management_project.repositoryPl.PaymentRepository;
import com.example.hotel_management_project.repositoryPl.RoomRepository;

import jakarta.validation.ValidationException;

@Service
public class PaymentDetailsService {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PaymentDetailsService.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private CustomerLogRepository customerLogRepository;
	
	public Optional<PaymentDetailsEntity> getPaymentDetailsById(Long id) {
		
		if(id == null || id <= 0) {
			throw new ValidationException("Id must be greater than 0");
		}
		
		return paymentRepository.findById(id);
	}
	
	public List<PaymentDetailsEntity> getAllPaymentDetails() {
		return paymentRepository.findAll();
	}
	
	public List<PaymentDetailsEntity> findPaymentDetailsByPaymentMethod(String paymentMethod) {
		if(paymentMethod == null) {
			throw new ValidationException("Payment cannot be null");
		}
		return paymentRepository.getPaymentDetailsByPaymentMethod(paymentMethod);
	}
	
	public PaymentDetailsEntity saveDetails(PaymentDetails payDetails) {
		
		if(payDetails.getStayDays() == null || payDetails.getStayDays() <= 0) {
			throw new ValidationException("StayDays cannot be less than 0");
		}
		
		PaymentDetailsEntity entity = new PaymentDetailsEntity();
		entity.setId(payDetails.getId());
		entity.setStayDays(payDetails.getStayDays());
		entity.setTotalPrice(payDetails.getTotalPrice());
		
		PaymentDetailsEntity savedPayment = paymentRepository.save(entity);
		logger.info("payment deatils saved successfully");
		CustomerDetailsEntity latestCustomer = customerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("No customer found"));
		logger.info("Fetched Last Customer to the application");
        RoomDetailsEntity latestRoom = roomRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("No room found"));
        logger.info("Last booked room fetched successfully");

        // Then, Create a Single Log Entry with all details
        CustomerLogsEntity logsEntity = new CustomerLogsEntity();
        logsEntity.setCustomerDetailsEntity(latestCustomer);
        logsEntity.setRoomDetailsEntity(latestRoom);
        logsEntity.setPaymentDetailsEntity(savedPayment);
        logsEntity.setLogTimestamp(LocalDateTime.now());

        customerLogRepository.save(logsEntity);
        logger.info("saved customer's details into Customerlogs");
        return savedPayment;
	}
	
	public PaymentDetailsEntity updateDetails(Long id, PaymentDetails payDetails) {
		
		PaymentDetailsEntity existingEntity = paymentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment details not found with id"+id));
		
		existingEntity.setStayDays(payDetails.getStayDays());
		existingEntity.setTotalPrice(payDetails.getTotalPrice());
		
		return paymentRepository.save(existingEntity);
	}
	
	public void deleteByPayment(Long id) {
		if(!paymentRepository.existsById(id)) {
			throw new RuntimeException("Payment details By Id not found"+id);
		}
		paymentRepository.deleteById(id); 
	}
}
