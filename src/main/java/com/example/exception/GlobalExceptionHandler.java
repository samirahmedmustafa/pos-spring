package com.example.exception;

import java.util.Date;
import java.util.NoSuchElementException;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Controller
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ItemNotFoundException.class)
	public ResponseEntity<?> handleBusinessException(ItemNotFoundException ex, WebRequest request) {
		logger.error("Invalid Input Business Exception: ", ex.getMessage());
		return new ResponseEntity<>(BusinessException.builder()
				.errorDate(new Date())
				.message(ex.getMessage())
				.code("604")
				.details(request.getDescription(false))
				.build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		logger.error("Invalid Input Exception: ", ex.getMessage());
		return new ResponseEntity<>(BusinessException.builder()
				.errorDate(new Date())
				.message(ex.getMessage())
				.code("605")
				.details(request.getDescription(false))
				.build(),
				HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
//		logger.error("Invalid Input Exception: ", ex.getMessage());
//		return new ResponseEntity<>(BusinessException.builder()
//				.errorDate(new Date())
//				.message(ex.getMessage())
//				.code("606")
//				.details(request.getDescription(false))
//				.build(),
//				HttpStatus.NOT_FOUND);
//	}

}