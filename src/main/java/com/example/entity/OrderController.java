package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderRepo;
import com.example.service.AbstractService;
import com.example.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController extends AbstractController<Order, Long> {

	public OrderController(OrderService service) {
		super(service);
	}
}
