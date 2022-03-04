package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.entity.City;
import com.example.entity.Country;
import com.example.entity.Customer;
import com.example.entity.InventoryDetail;
import com.example.entity.OrderAddress;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateCountryException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.exception.UserNotFoundException;
import com.example.repository.AddressRepo;
import com.example.repository.CategoryRepo;
import com.example.repository.CityRepo;
import com.example.repository.CountryRepo;
import com.example.repository.CustomerRepo;
import com.example.repository.ExpensePaymentRepo;
import com.example.repository.ExpenseRepo;
import com.example.repository.ExpenseTypeRepo;
import com.example.repository.InventoryDetailRepo;
import com.example.repository.InventoryPaymentRepo;
import com.example.repository.InventoryRepo;
import com.example.repository.NeighbourhoodRepo;
import com.example.repository.OrderAddressRepo;
import com.example.repository.OrderDetailRepo;
import com.example.repository.OrderPaymentRepo;
import com.example.repository.OrderRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import com.example.repository.ShipperRepo;
import com.example.repository.StatusRepo;
import com.example.repository.SupplierRepo;
import com.example.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RequiredArgsConstructor
public abstract class PosService<T, ID> {

	protected final JpaRepository<T, ID> repository;

	public PosService(JpaRepository<T, ID> repository) {
		super();
		this.repository = repository;
	}

	public T findById(ID id) {
		T t = repository.findById(id).orElseThrow(() -> new NoSuchElementException());
		return t;
	}
	
	public List<T> findAll() {
		List<T> t = repository.findAll();
		return t;
	}
	
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
	public T update(T t) {
		T updated = repository.save(t);
		return updated;
	}
	
	public T save(T t) {
		T saved = repository.save(t);
		return saved;
	}
}
