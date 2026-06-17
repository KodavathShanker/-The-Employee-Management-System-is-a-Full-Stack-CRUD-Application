package com.employee.managementapi.exception;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(StaleObjectStateException.class)
	    public ResponseEntity<String> handleStaleObject(
	            StaleObjectStateException ex) {

	        return new ResponseEntity<>(
	                "Record already updated by another transaction",
	                HttpStatus.CONFLICT);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGeneral(
	            Exception ex) {

	        return new ResponseEntity<>(
	                ex.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
