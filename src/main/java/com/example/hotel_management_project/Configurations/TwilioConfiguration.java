package com.example.hotel_management_project.Configurations;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;

public class TwilioConfiguration {
	
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
}
