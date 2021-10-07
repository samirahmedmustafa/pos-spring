package com.example.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ItemNotFoundException.class)
	public ResponseEntity<Object> handleBusinessNotFoundException(ItemNotFoundException ex, HttpServletRequest request) {
		ItemNotFoundException be = ItemNotFoundException.builder().message(ex.getMessage()).path(request.getRequestURI()).code("604").errorDate(new Date()).build();
		return new ResponseEntity<>(be, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DatabaseConstraintException.class)
	public ResponseEntity<Object> handleDatabaseConstraintException(DatabaseConstraintException ex, HttpServletRequest request) {
		DatabaseConstraintException be = DatabaseConstraintException.builder().message(ex.getLocalizedMessage()).path(request.getRequestURI()).code("605").errorDate(new Date()).build();
		return new ResponseEntity<>(be, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}