package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Customer;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;

@Service
@Transactional
public class CustomerService {

	private CustomerRepo CustomerRepo;

	public CustomerService(CustomerRepo CustomerRepo) {
		super();
		this.CustomerRepo = CustomerRepo;
	}

	public List<Customer> getCustomers() {
		return CustomerRepo.findAll();
	}

	public Customer getCustomerById(Long id) {
		Customer Customer = CustomerRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Customer with the id %d", id)));
		return Customer;
	}

	public Customer save(Customer Customer) {
		Customer savedCustomer = CustomerRepo.save(Customer);
		return savedCustomer;
	}

	public Customer update(Customer Customer, Long id) {
		Customer existingCustomer = CustomerRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Customer with the id %d", id)));
		Customer updatedCustomer = updateCustomer(existingCustomer, Customer);
		return CustomerRepo.save(updatedCustomer);
	}

	public void deleteById(Long id) {
		CustomerRepo.deleteById(id);
	}

	private Customer updateCustomer(Customer existingCustomer, Customer Customer) {
		existingCustomer.setAddress(Customer.getAddress());
		existingCustomer.setCity(Customer.getCity());
		existingCustomer.setCountry(Customer.getCountry());
		existingCustomer.setCompanyName(Customer.getCompanyName());
		existingCustomer.setContactName(Customer.getContactName());
		existingCustomer.setOrders(Customer.getOrders());
		existingCustomer.setPhone(Customer.getPhone());
		existingCustomer.setPostalCode(Customer.getPostalCode());
		existingCustomer.setRegion(Customer.getRegion());
		return existingCustomer;
	}

}
