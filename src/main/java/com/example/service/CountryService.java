package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Country;
import com.example.repository.CountryRepo;

@Service
@Transactional
public class CountryService extends AbstractService<Country, Integer>{

	public CountryService(CountryRepo repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

}
