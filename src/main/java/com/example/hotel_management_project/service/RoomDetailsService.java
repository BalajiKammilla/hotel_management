package com.example.hotel_management_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_project.dto.RoomDetails;
import com.example.hotel_management_project.entity.RoomDetailsEntity;
import com.example.hotel_management_project.mapper.RoomDetailsModelMapper;
import com.example.hotel_management_project.repositoryPl.RoomRepository;

import jakarta.validation.ValidationException;

@Service
public class RoomDetailsService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomDetailsModelMapper roomDetailsModelMapper;
		
	public Optional<RoomDetails> getRoomDetailsById(Long id) {
		if(id == null || id <= 0) {
			throw new ValidationException("Id should be greater than 0");
		}
		RoomDetailsEntity resultDetails = roomRepository.findById(id).get();
		return Optional.of(roomDetailsModelMapper.convertToDto(resultDetails));
	}
	
	public List<RoomDetailsEntity> getAllRoomDetials() {
		return roomRepository.findAll();
	}
	
	public List<RoomDetailsEntity> getRoomByroomType(String roomType){
		return roomRepository.getRoomDetailsByRoomType(roomType);
	}
	
	public RoomDetails saveDetails(RoomDetails roomDetails) {
		
		if(roomDetails.getRoomNo() == null) {
			throw new ValidationException("RoomNo cannot be null");
		}
		if(roomDetails.getIdProofType() == null || roomDetails.getIdProofType().isEmpty()) {
			throw new ValidationException("Id proof cannot be null or empty");
		}
		if(roomDetails.getCheckInType() == null || roomDetails.getCheckInType().isEmpty()) {
			throw new ValidationException("CheckInType cannot be null or empty");
		}
		if(roomDetails.getCheckoutTime() == null) {
			throw new ValidationException("Checkout Time cannot be null");
		}
	
		RoomDetailsEntity entity = new RoomDetailsEntity();
		entity.setRoomNo(roomDetails.getRoomNo());
		entity.setPrice(roomDetails.getPrice());
		entity.setIdProofType(roomDetails.getIdProofType());
		entity.setCheckInType(roomDetails.getCheckInType());
		entity.setCheckoutTime(roomDetails.getCheckoutTime());

		RoomDetailsEntity savedDetails = roomRepository.save(entity);
		return roomDetailsModelMapper.convertToDto(savedDetails);
	}
	
	public RoomDetails updateDetails(Long id, RoomDetails roomDetails) {
		
		RoomDetailsEntity existingEntity = roomRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Room not found iwith id "+id));
		
		existingEntity.setRoomNo(roomDetails.getRoomNo());
		existingEntity.setPrice(roomDetails.getPrice());
		existingEntity.setCheckInType(roomDetails.getCheckInType());
		existingEntity.setCheckoutTime(roomDetails.getCheckoutTime());
		
		RoomDetailsEntity updatedDetails = roomRepository.save(existingEntity);
		return roomDetailsModelMapper.convertToDto(updatedDetails);
	}
	
	public void deleteRoom(Long id) {
		if(!roomRepository.existsById(id)) {
			throw new RuntimeException("room not found id"+id);
		}
		 roomRepository.deleteById(id);
	}
}
