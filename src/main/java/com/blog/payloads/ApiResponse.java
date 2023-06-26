package com.blog.payloads;

public class ApiResponse {
	
	String message;
	boolean  status ;
	
	
	public ApiResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean  status) {
		this.status = status;
	}
	
	

}
