package com.example.hotel_management_project.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.hotel_management_project.enums.RoomStatus;
import com.example.hotel_management_project.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_details")
public class RoomDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "roomno")
	private Long roomNo;
	
	@Column(name = "roomtype")
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	@Column(name = "roomstatus")
	@Enumerated(EnumType.STRING)
	private RoomStatus roomStatus;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "checkintype")
	private String checkInType;
	
	@Column(name = "idprooftype")
	private String idProofType;
	
	@Column(name = "checkouttime")
	@DateTimeFormat
	private LocalDateTime checkoutTime;
	
	@OneToMany(mappedBy = "roomDetailsEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CustomerLogsEntity> logs = new ArrayList<>();
	
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
	public List<CustomerLogsEntity> getLogs() {
		return logs;
	}
	public void setLogs(List<CustomerLogsEntity> logs) {
		this.logs = logs;
	}
	@Override
	public String toString() {
		return "RoomDetailsEntity [id=" + id + ", roomNo=" + roomNo + ", roomType=" + roomType + ", roomStatus="
				+ roomStatus + ", price=" + price + ", checkInType=" + checkInType + ", idProofType=" + idProofType
				+ ", checkoutTime=" + checkoutTime + ", logs=" + logs + "]";
	}


}


