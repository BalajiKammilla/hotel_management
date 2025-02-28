package com.example.hotel_management_project.dto;

import java.time.LocalDateTime;

import com.example.hotel_management_project.enums.RoomStatus;
import com.example.hotel_management_project.enums.RoomType;

public class RoomDetails {
	
	private Long id;
	private Long roomNo;
	private RoomType roomType;
	private RoomStatus roomStatus;
	private Double price;
	private String checkInType;
	private String idProofType;
	private LocalDateTime checkoutTime = LocalDateTime.now();
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Long roomNo) {
		this.roomNo = roomNo;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public RoomStatus getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDateTime getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(LocalDateTime checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	public String getCheckInType() {
		return checkInType;
	}
	public void setCheckInType(String checkInType) {
		this.checkInType = checkInType;
	}
	public String getIdProofType() {
		return idProofType;
	}
	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}
	@Override
	public String toString() {
		return "RoomDetails [id=" + id + ", roomNo=" + roomNo + ", roomType=" + roomType + ", roomStatus=" + roomStatus
				+ ", price=" + price + ", checkInType=" + checkInType + ", idProofType=" + idProofType
				+ ", checkoutTime=" + checkoutTime + "]";
	}	
}
