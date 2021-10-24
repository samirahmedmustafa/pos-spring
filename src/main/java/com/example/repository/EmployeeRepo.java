package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	@Query("select e from Employee e where e.accountId = ?1")
	Optional<Employee> getByAccountId(String accountId);
	
	@Query("select e from Employee e where (e.accountId = ?1 AND e.password = ?2) OR (e.email = ?1 AND e.password = ?2)")
	Optional<Employee> login(String accountId, String password);
	
	@Query("select e from Employee e where e.email = ?1")
	Optional<Employee> getByEmail(String email);
}
