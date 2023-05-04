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

import com.example.entity.Country;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateCountryException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.CountryRepo;
import com.example.repository.OrderDetailRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class OrderDetailService extends PosService<OrderDetail, Long> {
	private final OrderDetailRepo repository;

	
	public OrderDetailService(OrderDetailRepo repository) {
		super(repository);
		this.repository = repository;
	}


	public List<OrderDetail> getByOrder(Long orderNo) {
		List<OrderDetail> orderDetails = repository.findByOrder(orderNo)
				.orElseThrow(() -> new NoSuchElementException("Invalid order details found"));
		return orderDetails;
	}
}
