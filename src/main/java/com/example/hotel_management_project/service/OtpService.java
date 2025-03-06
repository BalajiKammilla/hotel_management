package com.example.hotel_management_project.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	@Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
        System.out.println("DEBUG: Twilio Initialized with phone: " + twilioPhoneNumber);
    }
	
	private final Map<String, String> otpStorage = new HashMap<>();
	
	public String sendOtp(String mobileNumber) {
		 if (!mobileNumber.matches("\\+\\d{10,15}")) {
	            return "Invalid PhoneNumber Format. Use E.164 format (e.g., +14155552671).";
	        }
		
		String otp = generateOtp();
		otpStorage.put(mobileNumber, otp);
		try {
			Message.creator(
					new PhoneNumber(mobileNumber),
					new PhoneNumber(twilioPhoneNumber),
					"Your One Time Passcode For Your PassWord Reset is :"+ otp
					).create();
		}catch(Exception ex) {
			return "Failed to Send OTP:"+ ex.getMessage();
		}
		return "OTP sent Successfully !";
	}
	
	public boolean verifyOtp(String mobileNumber, String otp) {
		return otpStorage.containsKey(mobileNumber) && otpStorage.get(mobileNumber).equals(otp) ;
	}
	
	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

}
