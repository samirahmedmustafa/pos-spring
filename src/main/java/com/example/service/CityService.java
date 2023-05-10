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

import com.example.entity.City;
import com.example.entity.Order;
import com.example.entity.Role;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.CityRepo;
import com.example.repository.OrderRepo;
import com.example.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class CityService extends PosService<City, Long> {
	
	private final CityRepo repository;	
	
	public CityService(CityRepo repository) {
		super(repository);
		this.repository = repository;
	}

	public List<City> findCitiesByCountry(String country) {
		List<City> cities = repository.findByCountry(country);
		return cities;
	}

}
