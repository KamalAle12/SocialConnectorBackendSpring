package com.kamal.SocialMedia.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
	
	private String message;
	private String error;
	private LocalDateTime timestamp;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public ErrorDetails() {
		
	}
	public ErrorDetails(String message, String error, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.error = error;
		this.timestamp = timestamp;
	}
	
	
	
	
}
