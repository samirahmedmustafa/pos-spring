package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.ExpensePayment;

public interface ExpensePaymentRepo extends JpaRepository<ExpensePayment, Long> {

}
