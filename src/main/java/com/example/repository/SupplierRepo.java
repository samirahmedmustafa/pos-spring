package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
