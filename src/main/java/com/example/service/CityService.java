package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.City;
import com.example.entity.Country;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;

@Service
@Transactional
public class CityService {

	private CityRepo cityRepo;

	public CityService(CityRepo cityRepo) {
		super();
		this.cityRepo = cityRepo;
	}

	public List<City> getAll() {
		return cityRepo.findAll();
	}

	public City getById(Integer id) {
		City city = cityRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", id)));
		return city;
	}
	
	public List<City> getCitiesByCountry(String country) {
		List<City> cities = cityRepo.findCitiesByCountry(country)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", country)));
		return cities;
	}

	public City save(City city) {
		City savedCity = cityRepo.save(city);
		return savedCity;
	}

	public City update(City city, Integer id) {
		cityRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", id)));
		return cityRepo.save(city);
	}

	public void deleteById(Integer id) {
		cityRepo.deleteById(id);
	}
}
