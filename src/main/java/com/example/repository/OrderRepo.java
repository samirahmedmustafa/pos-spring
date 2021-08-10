package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
