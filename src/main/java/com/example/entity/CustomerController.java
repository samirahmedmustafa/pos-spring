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
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
        }

        @GetMapping("{id}")
        public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
                Customer customer = customerService.getCustomerById(id);
                return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }

        @PostMapping("")
        public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
                Customer savedCustomer = customerService.save(customer);
                return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
        }

        @PutMapping("{id}")
        public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
                Customer updatedCustomer = customerService.update(customer, id);
                return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> updateCustomer(@PathVariable Long id) {
                customerService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
        }

}
