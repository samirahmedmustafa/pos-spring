package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.exception.ItemNotFoundException;
import com.example.repository.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {

	private EmployeeRepo employeeRepo;

	public EmployeeService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	public Employee getEmployeeById(Integer id) {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find employee with the id %d", id)));
		return employee;
	}

	public Employee save(Employee employee) {
		Employee savedemployee = employeeRepo.save(employee);
		return savedemployee;
	}

	public Employee update(Employee employee, Integer id) {
		Employee existingEmployee = employeeRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find employee with the id %d", id)));
		Employee updatedEmployee = updateEmployee(existingEmployee, employee);
		return employeeRepo.save(updatedEmployee);
	}

	public void deleteById(Integer id) {
		employeeRepo.deleteById(id);
	}

	private Employee updateEmployee(Employee existingemployee, Employee employee) {
		existingemployee.setAddress(employee.getAddress());
		existingemployee.setCity(employee.getCity());
		existingemployee.setCountry(employee.getCountry());
		existingemployee.setRegion(employee.getRegion());
		existingemployee.setTitle(employee.getTitle());
		existingemployee.setBirthDay(employee.getBirthDay());
		existingemployee.setFirstName(employee.getFirstName());
		existingemployee.setLastName(employee.getLastName());
		existingemployee.setPassword(employee.getPassword());
		existingemployee.setNotes(employee.getNotes());
		existingemployee.setIsActive(employee.getIsActive());
		existingemployee.setOrders(employee.getOrders());
		existingemployee.setPhoto(employee.getPhoto());
		existingemployee.setReportsTo(employee.getReportsTo());
		return existingemployee;
	}

}
