package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Country;
import com.google.common.base.Optional;

public interface CountryRepo extends JpaRepository<Country, Integer> {

//	@Query("select c from Country c where c.code = ?1")
	Country findByCode(String code);
	
//	@Query("select c from Country c where c.name= ?1")
	Country findByName(String name);
}
