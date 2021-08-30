package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.OrderAddress;

public interface OrderAddressRepo extends JpaRepository<OrderAddress, Long> {

}
