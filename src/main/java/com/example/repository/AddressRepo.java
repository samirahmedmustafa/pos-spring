package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
