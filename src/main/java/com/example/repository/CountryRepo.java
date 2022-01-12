package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Country;
import com.google.common.base.Optional;

public interface CountryRepo extends JpaRepository<Country, Integer> {

	@Query("select c from Country c where c.code = ?1")
	Optional<Country> getCountryByCode(String code);
	
	@Query("select c from Country c where c.name= ?1")
	Optional<Country> getCountryByName(String name);
}
