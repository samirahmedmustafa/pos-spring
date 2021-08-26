package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.ExpenseType;

public interface ExpenseTypeRepo extends JpaRepository<ExpenseType, Integer> {

}
