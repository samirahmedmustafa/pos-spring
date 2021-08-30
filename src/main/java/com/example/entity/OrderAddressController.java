package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderAddressRepo;

@RestController
@RequestMapping("orderAddresses")
public class OrderAddressController extends AbstractController<OrderAddressRepo, OrderAddress, Long> {

	public OrderAddressController(OrderAddressRepo r) {
		super(r);
	}
}
