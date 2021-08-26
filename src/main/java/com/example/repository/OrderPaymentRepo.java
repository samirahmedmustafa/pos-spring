package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.OrderPayment;

public interface OrderPaymentRepo extends JpaRepository<OrderPayment, Long> {

}
