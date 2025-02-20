package com.example.hotel_management_project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "customer_logs")
public class CustomerLogsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerDetailsEntity customerDetailsEntity;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private RoomDetailsEntity roomDetailsEntity;
	
	@ManyToOne
	@JoinColumn(name = "payment_id", nullable = false)
	private PaymentDetailsEntity paymentDetailsEntity;
	
	private LocalDateTime logTimestamp = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerDetailsEntity getCustomerDetailsEntity() {
		return customerDetailsEntity;
	}

	public void setCustomerDetailsEntity(CustomerDetailsEntity customerDetailsEntity) {
		this.customerDetailsEntity = customerDetailsEntity;
	}

	public RoomDetailsEntity getRoomDetailsEntity() {
		return roomDetailsEntity;
	}

	public void setRoomDetailsEntity(RoomDetailsEntity roomDetailsEntity) {
		this.roomDetailsEntity = roomDetailsEntity;
	}

	public PaymentDetailsEntity getPaymentDetailsEntity() {
		return paymentDetailsEntity;
	}

	public void setPaymentDetailsEntity(PaymentDetailsEntity paymentDetailsEntity) {
		this.paymentDetailsEntity = paymentDetailsEntity;
	}

	public LocalDateTime getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(LocalDateTime logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	@Override
	public String toString() {
		return "CustomerLogsEntity [id=" + id + ", customerDetailsEntity=" + customerDetailsEntity
				+ ", roomDetailsEntity=" + roomDetailsEntity + ", paymentDetailsEntity=" + paymentDetailsEntity
				+ ", logTimestamp=" + logTimestamp + "]";
	}	
}
