package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderRepo;

@RestController
@RequestMapping("orders")
public class OrderController extends AbstractController<OrderRepo, Order, Long> {

	public OrderController(OrderRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
