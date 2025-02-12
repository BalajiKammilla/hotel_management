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
@Table(name = "customer_details")
public class CustomerDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "customername")
	private String customerName;
	
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
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "CustomerDetailsEntity [id=" + id + ", customerName=" + customerName + ", age=" + age + ", mobileNumber="
				+ mobileNumber + ", password=" + password + ", countryCode=" + countryCode + ", address=" + address
				+ ", idProof=" + idProof + ", maritalStatus=" + maritalStatus + "]";
	}
	
}

enum MaritalStatus {
	MARRIED,
	UNMARRIED,
	DIVORCED,
	NOTDEFINED,
}
