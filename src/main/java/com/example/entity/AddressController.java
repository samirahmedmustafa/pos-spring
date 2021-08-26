package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.AddressRepo;

@RestController
@RequestMapping("addresses")
public class AddressController extends AbstractController<AddressRepo, Address, Integer> {

	public AddressController(AddressRepo r) {
		super(r);
	}
}
