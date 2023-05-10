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
import com.example.entity.Address;
import com.example.entity.PaymentType;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.entity.Shipper;
import com.example.entity.Supplier;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.AddressRepo;
import com.example.repository.PaymentTypeRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import com.example.repository.ShipperRepo;
import com.example.repository.StatusRepo;
import com.example.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class PaymentTypeService extends PosService<PaymentType, Integer> {
	private final PaymentTypeRepo repository;

	public PaymentTypeService(PaymentTypeRepo repository) {
		super(repository);
		this.repository = repository;
	}

}
