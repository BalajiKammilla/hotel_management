package com.example.hotel_management_project.exception;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}
}
