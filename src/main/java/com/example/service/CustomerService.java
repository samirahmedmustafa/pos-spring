package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Customer;
import com.example.entity.Employee;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.EmployeeRepo;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerById(Long id) {
		Customer customer = customerRepo.findById(id).get();
		return customer;
	}
	
	public Customer getCustomerByPhone(String phone) {
		Customer customer = customerRepo.getByPhone(phone).get();
		return customer;
	}

	public Customer save(Customer customer) {
		Customer saved = customerRepo.save(customer);
		return saved;
	}

	public Customer update(Customer customer, Long id) {
		customerRepo.findById(id).get();
		Customer updated = customerRepo.save(customer);
		return updated;
	}

	public void deleteById(Long id) {
		customerRepo.deleteById(id);
	}
}
