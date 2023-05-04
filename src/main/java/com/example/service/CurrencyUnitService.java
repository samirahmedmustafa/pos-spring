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
import com.example.entity.Category;
import com.example.entity.CurrencyUnit;
import com.example.entity.Customer;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CategoryRepo;
import com.example.repository.CurrencyUnitRepo;
import com.example.repository.CustomerRepo;

@Service
@Transactional
public class CurrencyUnitService extends PosService<CurrencyUnit, Long> {
	private final CurrencyUnitRepo repository;

	public CurrencyUnitService(CurrencyUnitRepo repository) {
		super(repository);
		this.repository = repository;
	}	

	
}
