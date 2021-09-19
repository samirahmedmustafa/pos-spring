package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Customer;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerById(Long id) {
		Customer customer = customerRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Find error: Invalid customer id")));
		return customer;
	}

	public Customer getCustomerByPhone(String phone) {
		Customer customer = customerRepo.getByPhone(phone).get();
		return customer;
	}

	public Customer save(Customer customer) {

		if (customer.getPhone() == null || customer.getPhone() == "")
			throw new ItemNotFoundException("Invalid phone no.");

		if (customerRepo.getByPhone(customer.getPhone()).isPresent())
			throw new ItemNotFoundException("Customer already exists");

		Customer saved = customerRepo.save(customer);
		return saved;
	}

	public Customer update(Customer customer, Long id) {
		customerRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Update error: Invalid customer id")));
		Customer updated = customerRepo.save(customer);
		return updated;
	}

	public void deleteById(Long id) {
		customerRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Delete error: Invalid customer id")));
		customerRepo.deleteById(id);
	}
}
