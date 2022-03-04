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

import com.example.entity.Category;
import com.example.entity.Neighbourhood;
import com.example.entity.Order;
import com.example.entity.OrderAddress;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.CategoryRepo;
import com.example.repository.NeighbourhoodRepo;
import com.example.repository.OrderAddressRepo;
import com.example.repository.OrderRepo;
import com.example.repository.RoleRepo;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class CategoryService extends PosService<Category, Integer> {

	private final CategoryRepo repository;

	public CategoryService(CategoryRepo repository) {
		super(repository);
		this.repository = repository;
	}	

}
