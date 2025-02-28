package com.example.hotel_management_project.entity;


import java.util.ArrayList;
import java.util.List;

import com.example.hotel_management_project.enums.MaritalStatus;
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
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_details",
indexes = @Index(name = "idx_customer_id", columnList = "customerid"))
public class CustomerDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "customername")
	private String customerName;
	
	@Column(name = "customerid", unique = true, nullable = false)
	private String customerID;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "mobilenumber")
	private String mobileNumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "countrycode")
	private String countryCode;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "idproof")
	private String idProof;
	
	@Column(name = "maritalstatus")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	
	@OneToMany(mappedBy = "customerDetailsEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CustomerLogsEntity> logs = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public List<CustomerLogsEntity> getLogs() {
		return logs;
	}
	public void setLogs(List<CustomerLogsEntity> logs) {
		this.logs = logs;
	}
	@Override
	public String toString() {
		return "CustomerDetails [id=" + id + ", customerID=" + customerID + ", customerName=" + customerName + ", age="
				+ age + ", mobileNumber=" + mobileNumber + ", password=" + password + ", countryCode=" + countryCode
				+ ", address=" + address + ", idProof=" + idProof + ", maritalStatus=" + maritalStatus + "]";
	}

}

