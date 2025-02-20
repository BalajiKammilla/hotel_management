package com.example.hotel_management_project.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message) ;
	}
}
