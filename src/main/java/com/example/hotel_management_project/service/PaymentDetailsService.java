package com.example.hotel_management_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_management_project.dto.PaymentDetails;
import com.example.hotel_management_project.entity.PaymentDetailsEntity;
import com.example.hotel_management_project.repositoryPl.PaymentRepository;

import jakarta.validation.ValidationException;

@Service
public class PaymentDetailsService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
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
		
		return paymentRepository.save(entity);
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
