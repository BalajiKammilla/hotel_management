package com.example.hotel_management_project.dto;

public class OtpRequest {

	private String mobileNumber;
	private String otp;
	private String newPassword;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "OtpRequest [mobileNumber=" + mobileNumber + ", otp=" + otp + ", newPassword=" + newPassword + "]";
	}
	
}
