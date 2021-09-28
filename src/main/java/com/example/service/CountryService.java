package com.example.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.City;
import com.example.entity.Country;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;
import com.example.repository.CountryRepo;

@Service
@Transactional
public class CountryService extends AbstractService<Country, Integer>{

	public CountryService(CountryRepo repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

}
