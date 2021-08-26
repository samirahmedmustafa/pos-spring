package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.InventoryPayment;

public interface InventoryPaymentRepo extends JpaRepository<InventoryPayment, Long> {

}
