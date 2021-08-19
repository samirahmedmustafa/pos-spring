package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.TransactionType;

public interface TransactionTypeRepo extends JpaRepository<TransactionType, Integer> {

}