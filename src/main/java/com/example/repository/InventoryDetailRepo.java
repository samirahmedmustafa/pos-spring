package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.InventoryDetail;

public interface InventoryDetailRepo extends JpaRepository<InventoryDetail, Long> {

}
