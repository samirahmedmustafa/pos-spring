package com.example.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.constant.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandling implements ErrorController {

	public static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact the administrator";
	public static final String INVALID_USER_OR_ROLE = "Invalid user or role";
	public static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed in this endpoint. Please send a '%s' request";
	public static final String INTERNAL_SERVER_ERROR_MSG = "An error occured while processing the request";
	public static final String PAGE_NOT_FOUND = "Page is not found";
	public static final String INCORRECT_CREDENTIALS = "Username/Password incorrect. Please try again";
	public static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact the administration";
	public static final String NO_SUCH_ELEMENT = "No such element found";
	public static final String EMPTY_ROLE_NAME = "Role name cannot be empty";
	public static final String ERROR_PROCESSING_FILE = "Error occured while processing file";
	public static final String NOT_ENOUGH_PERMISSION = "You don not have enough permissions";
	public static final String ERROR_PATH = "/error";
	
	@RequestMapping(ERROR_PATH)
	public ResponseEntity<HttpResponse> notFound404() {
		return createHttpResponse(HttpStatus.NOT_FOUND, "There is no such URL");
	}
	
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@ExceptionHandler(EmptyRoleNameException.class)
	public ResponseEntity<HttpResponse> emptyRoleNameException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, EMPTY_ROLE_NAME);
	}
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<HttpResponse> accountDisabledException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<HttpResponse> badCredentialsException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<HttpResponse> noHandlerFoundException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, PAGE_NOT_FOUND);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HttpResponse> accessDeniedException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, NOT_ENOUGH_PERMISSION);
	}
	
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<HttpResponse> lockedException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_LOCKED);
	}
	
	@ExceptionHandler(InvalidUserOrRoleException.class)
	public ResponseEntity<HttpResponse> invalidUserOrRoleException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, INVALID_USER_OR_ROLE);
	}
	
	@ExceptionHandler(DuplicateCountryException.class)
	public ResponseEntity<HttpResponse> duplicateCountryException(DuplicateCountryException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<HttpResponse> duplicateEmailException(DuplicateEmailException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(DuplicateUsernameException.class)
	public ResponseEntity<HttpResponse> duplicateEmailException(DuplicateUsernameException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<HttpResponse> usernameNotFoundException(UsernameNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(CountryNotFoundException.class)
	public ResponseEntity<HttpResponse> countryNotFoundException(CountryNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<HttpResponse> noSuchElementException(NoSuchElementException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
		return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<HttpResponse> noResultException(NoResultException exception) {
		return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<HttpResponse> ioException(IOException exception) {
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponse> exception(Exception exception) {
		log.error("exception: ", exception.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<HttpResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		HttpMethod httpMethod = exception.getSupportedHttpMethods().iterator().next();
		return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, httpMethod));
	}
	
	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		HttpResponse httpResponse = new HttpResponse(httpStatus.value(), 
				httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase(), new Date());
		return new ResponseEntity<HttpResponse>(httpResponse, httpStatus);
	}
	
}
