package com.example.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.City;

public interface CityRepo extends JpaRepository<City, Long> {
//	@Query("select c from City c where c.country.name = ?1")
	List<City> findByCountry(String country);
}
