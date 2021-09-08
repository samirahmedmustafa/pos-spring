package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.exception.ItemNotFoundException;
import com.example.repository.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	public Employee getEmployeeById(Integer id) {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find employee with the id %d", id)));
		return employee;
	}

	public Employee save(Employee employee) {
		Employee saved = employeeRepo.save(employee);
		return saved;
	}

	public Employee update(Employee employee, Integer id) {
		employeeRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find employee with the id %d", id)));
		Employee updated = employeeRepo.save(employee);
		return updated;
	}

	public void deleteById(Integer id) {
		employeeRepo.deleteById(id);
	}
}
