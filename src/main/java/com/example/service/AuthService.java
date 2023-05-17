package com.example.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.AuthController;
import com.example.entity.AuthenticationRequest;
import com.example.entity.AuthenticationResponse;
import com.example.entity.Employee;
import com.example.entity.RegisterRequest;
import com.example.entity.Role;
import com.example.exception.UserNotFoundException;
import com.example.repository.EmployeeRepo;
import com.example.security.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
	private final EmployeeRepo employeeRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		
		var employee = employeeRepo.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException(String.format("Employee %s not found", request.getEmail())));
		
		var jwtToken = jwtService.generateToken(employee);
		return AuthenticationResponse.builder().token(jwtToken).build();
		
	}

	public AuthenticationResponse register(Employee request) {

//		Role role = new Role();
//		Set<Role> roles = new HashSet<>();

		logger.error(request.getFirstName());

		Employee employee = new Employee();
		
		employee.setEmail(request.getEmail());
		employee.setPassword(passwordEncoder.encode(request.getPassword()));
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
//		role.setName("Admin");
//		roles.add(role);
		
		employee.setRoles(request.getRoles());

		employeeRepo.save(employee);
		var jwtToken = jwtService.generateToken(employee);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
