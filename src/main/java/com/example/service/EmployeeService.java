package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.exception.ItemNotFoundException;
import com.example.repository.EmployeeRepo;

@Service
@Transactional
public class EmployeeService extends AbstractService<Employee, Integer> {

	public EmployeeService(EmployeeRepo repository) {
		super(repository);
	}
}
