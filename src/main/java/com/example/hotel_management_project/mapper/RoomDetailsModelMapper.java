package com.example.hotel_management_project.mapper;

import org.modelmapper.ModelMapper;

import com.example.hotel_management_project.dto.RoomDetails;
import com.example.hotel_management_project.entity.RoomDetailsEntity;

public class RoomDetailsModelMapper {
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	public RoomDetails convertToDto(RoomDetailsEntity entity) {
		return modelMapper.map(entity, RoomDetails.class);
	}
	
	public RoomDetailsEntity convertToEntity(RoomDetails dto) {
		return modelMapper.map(dto, RoomDetailsEntity.class);
	}

}
