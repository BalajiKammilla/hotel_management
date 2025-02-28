package com.example.hotel_management_project.dto;

import com.example.hotel_management_project.enums.MaritalStatus;

public class CustomerDetails {
	
	private Long id;
	private String customerID;
	private String customerName;
	private int age;
	private String mobileNumber;
	private String password;
	private String countryCode;
	private String address;
	private String idProof;
	private MaritalStatus maritalStatus = MaritalStatus.NOTDEFINED;
	
	
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
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public String toString() {
		return "CustomerDetails [id=" + id + ", customerID=" + customerID + ", customerName=" + customerName + ", age="
				+ age + ", mobileNumber=" + mobileNumber + ", password=" + password + ", countryCode=" + countryCode
				+ ", address=" + address + ", idProof=" + idProof + ", maritalStatus=" + maritalStatus + "]";
	}
}
