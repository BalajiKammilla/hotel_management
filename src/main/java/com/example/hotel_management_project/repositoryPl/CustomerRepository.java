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
		CustomerDetailsEntity findByCustomerName(String username);
		Optional<CustomerDetailsEntity> findByMobileNumber(String mobileNumber);
		Optional<CustomerDetailsEntity> findByCustomerID(String customerID);
		Optional<CustomerDetailsEntity> findTopByCustomerIDStartingWithOrderByCustomerIDDesc(String prefix);
		Optional<CustomerDetailsEntity> findTopByOrderByIdDesc();
}
