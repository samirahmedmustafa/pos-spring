package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

}
