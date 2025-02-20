package com.example.hotel_management_project.repositoryPl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_management_project.entity.CustomerLogsEntity;

@Repository
public interface CustomerLogRepository extends JpaRepository<CustomerLogsEntity, Long>{
	
}
