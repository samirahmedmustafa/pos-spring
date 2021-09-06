package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.City;
import com.example.entity.Country;

public interface CityRepo extends JpaRepository<City, Integer> {
	@Query("select c from City c where c.country.name = ?1")
	Optional<List<City>> findCitiesByCountry(String country);
}
