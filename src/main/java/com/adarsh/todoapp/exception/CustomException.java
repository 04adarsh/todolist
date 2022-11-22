package com.adarsh.todoapp.exception;

public class CustomException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;
	
	public CustomException(String message) {
		super(message);
	}
	

}
