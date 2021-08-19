package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.PaymentType;

public interface PaymentTypeRepo extends JpaRepository<PaymentType, Integer> {

}
