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

import com.example.entity.Category;
import com.example.entity.Country;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateCountryException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.CategoryRepo;
import com.example.repository.CountryRepo;
import com.example.repository.CustomerRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class CountryService extends PosService<Country, Integer> {

	private final CountryRepo repository;
	
	public CountryService(CountryRepo repository) {
		super(repository);
		this.repository = repository;
	}

	public Country getCountryByCode(String code) {
		Country country = repository.findByCode(code);
		return country;
	}

	public Country findByName(String name) {
		Country country = repository.findByName(name);
		return country;
	}
	
	private Boolean isCountryCodeExist(String code) {
		Country country = repository.findByCode(code);

		if (country != null)
			throw DatabaseConstraintException.builder()
					.message(String.format("Country code %s already exists in the database", (code))).build();

		return false;
	}

	private Boolean isCountryNameExist(String name) {

		Country country = repository.findByName(name);

		if (country != null)
			throw DatabaseConstraintException.builder()
					.message(String.format("Country name %s already exists in the database", (name))).build();

		return false;
	}

}
