package com.example.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.UserNotFoundException;
import com.example.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController { 

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody Employee request) {
		logger.error(request.getFirstName());
		return new ResponseEntity<AuthenticationResponse>(authService.register(request), HttpStatusCode.valueOf(204));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws UserNotFoundException {
		return new ResponseEntity<AuthenticationResponse>(authService.authenticate(request), HttpStatusCode.valueOf(200));
	}
	
}
