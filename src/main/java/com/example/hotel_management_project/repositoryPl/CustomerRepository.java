package com.example.hotel_management_project.repositoryPl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_management_project.entity.CustomerDetailsEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetailsEntity, Long>  {
	
		List<CustomerDetailsEntity> getCustomerDetailsByCustomerName(String customerName);
		boolean existsByMobileNumber(String mobileNumber);
//		added for spring security use
		CustomerDetailsEntity findByCustomerName(String username);
		Optional<CustomerDetailsEntity> findByMobileNumber(String mobileNumber);
}
