package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {

}
