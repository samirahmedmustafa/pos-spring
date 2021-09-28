package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderAddressRepo;
import com.example.service.AbstractService;
import com.example.service.OrderAddressService;

@RestController
@RequestMapping("orderAddresses")
public class OrderAddressController extends AbstractController<OrderAddress, Long> {

	public OrderAddressController(OrderAddressService service) {
		super(service);
	}
}
