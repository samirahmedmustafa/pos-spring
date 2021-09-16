package com.example.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<ResponseDto> handleIllegalArgumentException(NoSuchElementException exception) {
		return new ResponseEntity<>(ResponseDto.builder().message("invalid id no.").success(false).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<ResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		return new ResponseEntity<>(ResponseDto.builder().message("item with one or multiple inputs already exists").success(false).build(), HttpStatus.BAD_REQUEST);
	}
}
