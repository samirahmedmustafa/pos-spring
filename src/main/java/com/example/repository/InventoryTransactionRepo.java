package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.InventoryTransaction;

public interface InventoryTransactionRepo extends JpaRepository<InventoryTransaction, Long> {

}
