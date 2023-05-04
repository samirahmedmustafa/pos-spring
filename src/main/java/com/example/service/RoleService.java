package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.RoleRepo;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class RoleService extends PosService<Role, Long> {
	
	private final RoleRepo repository;

	public RoleService(RoleRepo repository) {
		super(repository);
		this.repository = repository;
	}

	public Role findRoleByName(String name) {
		Role role = repository.findByName(name);
		if (role == null)
			throw new NoSuchElementException("Role not found");
		return role;
	}

	public Role findByRoleName(String roleName) {
		Role role = repository.findByName(roleName);
		return role;
	}
}
