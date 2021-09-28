package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.AbstractService;
import com.example.service.AddressService;

@RestController
@RequestMapping("addresses")
public class AddressController extends AbstractController<Address, Long> {
	
	public AddressController(AddressService service) {
		super(service);
	}
}
