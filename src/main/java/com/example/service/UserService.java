package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.RoleRepo;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserService extends PosService<User, Long> implements UserDetailsService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepo repository;
	private final RoleRepo roleRepo;

	public UserService(PasswordEncoder passwordEncoder, UserRepo repository, RoleRepo roleRepo) {
		super(repository);
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
		this.roleRepo = roleRepo;
	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User saved = repository.save(user);
		return saved;
	}
	
	public User findUserByEmail(String email) {
		User user = repository.findByEmail(email);

		if (user == null)
			throw new NoSuchElementException("User not found");

		return user;
	}

	private Boolean isUsernameExists(String username) throws DuplicateUsernameException {

		User user = repository.findByUsername(username);

		if (user != null)
			throw new DuplicateUsernameException(username);

		return false;
	}

	public void addUserRole(String username, String roleName) throws InvalidUserOrRoleException {
		User user = repository.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		validateUserRole(user, role);
		user.getRoles().add(role);
		repository.save(user);
	}

	public void removeUserRole(String username, String roleName) throws InvalidUserOrRoleException {
		User user = repository.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		validateUserRole(user, role);
		user.getRoles().remove(role);
		repository.save(user);
	}

	private void validateUserRole(User user, Role role) throws InvalidUserOrRoleException {
		if (user == null || role == null)
			throw new InvalidUserOrRoleException(ExceptionHandling.INVALID_USER_OR_ROLE);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("loadUserByUsername authenticating User: {}", username);

		User user = repository.findByUsername(username);
		if (user == null) {
			log.error("loadUserByUsername User not found");
			throw new UsernameNotFoundException("loadUserByUsername User not found");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	private Boolean isUserEmailExist(String email) throws DuplicateEmailException {

		User user = repository.findByEmail(email);

		if (user != null)
			throw new DuplicateEmailException(email);

		return false;
	}

	private Boolean isEmailEmpty(String email) {

		Boolean emailIsEmpty = (email == null || email.replaceAll("\\s+", "").length() == 0);

		if (emailIsEmpty)
			throw DatabaseConstraintException.builder().message(String.format("Employee email is invalid", email))
					.build();

		return false;
	}

	public void isPasswordNotEmpty(String password) {

		if (password == null || password.replaceAll("\\s+", "").length() == 0)
			throw DatabaseConstraintException.builder().message(String.format("Employee password cannot be null"))
					.build();
	}

	public User findUserByUsername(String username) {
		User user = repository.findByUsername(username);

		if (user == null)
			throw new NoSuchElementException("User not found");

		return user;
	}

}
