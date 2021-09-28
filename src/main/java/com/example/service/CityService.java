package com.example.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.City;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;

@Service
@Transactional
public class CityService extends AbstractService<City, Integer>{

	public CityService(CityRepo repository) {
		super(repository);
	}

}
