package com.example.hotel_management_project.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_details")
public class RoomDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "roomNo")
	private Long roomNo;
	
	@Column(name = "roomType")
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	@Column(name = "roomStatus")
	@Enumerated(EnumType.STRING)
	private RoomStatus roomStatus;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "checkInType")
	private String checkInType;
	
	@Column(name = "idProofType")
	private String idProofType;
	
	@DateTimeFormat
	private LocalDateTime checkoutTime;
	
	
	
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
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDateTime getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(LocalDateTime checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	@Override
	public String toString() {
		return "RoomDetailsEntity [id=" + id + ", roomNo=" + roomNo + ", roomType=" + roomType + ", roomStatus="
				+ roomStatus + ", price=" + price + ", checkInType=" + checkInType + ", idProofType=" + idProofType
				+ ", checkoutTime=" + checkoutTime + "]";
	}
	
}

enum RoomType {
	NONAC,
	AC,
}

enum RoomStatus {
	NOTAVAILABLE,
	AVAILABLE,
}

