package com.example.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.City;
import com.example.entity.Country;
import com.example.entity.Neighbourhood;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;
import com.example.repository.NeighbourhoodRepo;

@Service
@Transactional
public class NeighbourhoodService extends AbstractService<Neighbourhood, Integer>{

	public NeighbourhoodService(NeighbourhoodRepo repository) {
		super(repository);
	}

}
