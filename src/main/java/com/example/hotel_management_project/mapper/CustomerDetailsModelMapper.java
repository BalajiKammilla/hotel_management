package com.example.hotel_management_project.mapper;

import org.modelmapper.ModelMapper;

import com.example.hotel_management_project.dto.CustomerDetails;
import com.example.hotel_management_project.entity.CustomerDetailsEntity;

public class CustomerDetailsModelMapper {
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	public CustomerDetails convertToDto(CustomerDetailsEntity entity) {
		return modelMapper.map(entity, CustomerDetails.class);
	}
	
	public CustomerDetailsEntity convertToEntity(CustomerDetails dto) {
		return modelMapper.map(dto, CustomerDetailsEntity.class);
	}
 
}
