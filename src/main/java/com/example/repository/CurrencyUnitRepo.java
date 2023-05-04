package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;
import com.example.entity.CurrencyUnit;

public interface CurrencyUnitRepo extends JpaRepository<CurrencyUnit, Long> {

}
