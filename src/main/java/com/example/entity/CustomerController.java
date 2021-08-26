package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CustomerRepo;

@RestController
@RequestMapping("customers")
public class CustomerController extends AbstractController<CustomerRepo, Customer, Long> {

	public CustomerController(CustomerRepo r) {
		super(r);
	}
}
