package com.example.entity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateCountryException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.service.PosService;
import com.example.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = { "/", "/api/users" })
public class UserController extends PosController<User, Long> {

	private final UserService service;

	public UserController(UserService service) {
		super(service);
		this.service = service;
	}

	@PostMapping("addUserRole")
	public ResponseEntity<?> addUserRole(@RequestParam String username, @RequestParam String roleName) throws InvalidUserOrRoleException {
		log.info("username: {}", username);
		log.info("roleName: {}", roleName);
		service.addUserRole(username, roleName);
		return new ResponseEntity<>(HttpStatus.OK);		
	}

	@GetMapping("byUsername")
	public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
		User user = service.findUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
