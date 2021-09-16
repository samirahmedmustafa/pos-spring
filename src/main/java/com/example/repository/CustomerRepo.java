package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	@Query("select c from Customer c where c.phone = ?1")
	Optional<Customer> getByPhone(String phone);
}
