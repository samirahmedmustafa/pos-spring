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
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class ProductService extends PosService<Product, Long> {
	private final ProductRepo repository;

	public ProductService(ProductRepo repository) {
		super(repository);
		this.repository = repository;
	}

	public Product findByBarcode(String barcode) {
		Product product = repository.findByBarcode(barcode);
		return product;
	}

	public Product findProductByName(String name) {
		return repository.findByName(name);
	}
	
	public Product debitProduct(Long id, Integer quantity) {
		Product product = repository.getById(id);
		product.setCurrentStock(product.getCurrentStock() + quantity);
		product = repository.save(product);
		return product;
	}
}
