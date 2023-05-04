package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Address;
import com.example.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByAccountId(String name);
	Employee findByEmail(String email);
}
