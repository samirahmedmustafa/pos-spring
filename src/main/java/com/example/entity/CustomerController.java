package com.example.entity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerService.getCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("byPhone")
	public ResponseEntity<Customer> getByPhone(@RequestParam String phone) {
		Customer customer = customerService.getCustomerByPhone(phone);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer saved = customerService.save(customer);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<Customer> updateEmployee(@RequestBody Customer customer, @PathVariable Long id) {
		Customer updated = customerService.update(customer, id);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		customerService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
