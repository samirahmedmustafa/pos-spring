package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

}
