package com.example.hotel_management_project.repositoryPl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_management_project.entity.RoomDetailsEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomDetailsEntity, Long> {
	
	List<RoomDetailsEntity> getRoomDetailsByRoomType(String roomType);

	Optional<RoomDetailsEntity> findTopByOrderByIdDesc();
	
}
