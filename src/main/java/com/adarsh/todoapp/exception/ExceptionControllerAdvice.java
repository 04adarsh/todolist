package com.adarsh.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleResouceNotFoundException(CustomException customException) {
        return new ResponseEntity<>(customException.getMessage(), HttpStatus.NOT_FOUND);
    }
    
}