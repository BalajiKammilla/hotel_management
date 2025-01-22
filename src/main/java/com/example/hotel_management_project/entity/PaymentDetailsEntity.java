package com.example.hotel_management_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_details")
public class PaymentDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "stay_days")
	private Double stayDays;
	
	@Column(name ="total_price")
	private Double totalPrice;
	
	@Column(name ="payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getStayDays() {
		return stayDays;
	}
	public void setStayDays(Double stayDays) {
		this.stayDays = stayDays;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "PaymentDetailsEntity [id=" + id + ", stayDays=" + stayDays + ", totalPrice=" + totalPrice
				+ ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + "]";
	}
	
	
}

enum PaymentMethod {
	UPI,
	CREDITCARD,
	DEBITCARD,
	CASH,
}

enum PaymentStatus {
	SUCCESS,
	FAILED,
}
