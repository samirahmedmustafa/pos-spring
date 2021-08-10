package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Shipper;

public interface ShipperRepo extends JpaRepository<Shipper, Integer> {

}
