package com.example.hotel_management_project.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_management_project.dto.RoomDetails;
import com.example.hotel_management_project.entity.RoomDetailsEntity;
import com.example.hotel_management_project.service.RoomDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Room Details")
@RestController
@RequestMapping("room")
public class RoomDetailsResource {
	
	@Autowired
	private RoomDetailsService roomDetailsService;
	
	@GetMapping("/check")
	public String getString() {
		return "RoomDetails Resource";
	}
	
    @Operation(
            summary = "Retrieves RoomDetails By Id",
            description = "Fetches room details using the given ID. Returns a single room record if found."
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room details retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@GetMapping("/details/{id}")
	public Optional<RoomDetailsEntity> findById(@PathVariable Long id) {
		return roomDetailsService.getRoomDetailsById(id);
	}
	
    @Operation(
            summary = "Retrieves All RoomDetails",
            description = "Fetches all room details"
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@GetMapping
	public ResponseEntity<List<RoomDetailsEntity>> findAllDetails() {
		List<RoomDetailsEntity> list = roomDetailsService.getAllRoomDetials();
		return ResponseEntity.ok(list);
	}
	
    @Operation(
            summary = "Retrieves RoomDetails By The Type of Room",
            description = "Fetches room details using the type of room. Returns room records if found."
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room details retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@GetMapping("/{roomType}")
	public List<RoomDetailsEntity> findByRoomType(@PathVariable String roomType) {
		return roomDetailsService.getRoomByroomType(roomType);
	}
	
    
    @Operation(
            summary = "Persist RoomDetails",
            description = "save room details if room is available"
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room details saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@PostMapping("/save")
	public ResponseEntity<RoomDetailsEntity> saveDetails(@RequestBody RoomDetails details){
		RoomDetailsEntity roomDeatils = roomDetailsService.saveDetails(details);
		return ResponseEntity.status(200).body(roomDeatils);
	}
	
    
    @Operation(
            summary = "Update RoomDetails By Id",
            description = "update or alter room details using the given ID. Returns a single room record if found."
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room details Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@PutMapping("/update/{id}")
	public ResponseEntity<RoomDetailsEntity> updateDetails(@PathVariable Long id, @RequestBody RoomDetails details){
		
		RoomDetailsEntity updateEntity = roomDetailsService.updateDetails(id, details);
		return ResponseEntity.ok(updateEntity);
	}
	
    @Operation(
            summary = "Delete RoomDetails By Id",
            description = "Delete room details using the given ID"
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room details Deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RoomDetailsEntity> deleteBydetail(@PathVariable Long id){
		roomDetailsService.deleteRoom(id);
		return ResponseEntity.noContent().build();
	}

}
