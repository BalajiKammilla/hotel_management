package com.example.hotel_management_project.repositoryPl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_management_project.entity.PaymentDetailsEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetailsEntity, Long> {
	
	List<PaymentDetailsEntity> getPaymentDetailsByPaymentMethod(String paymentMethod);
}
