package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderDetailRepo;

@RestController
@RequestMapping("orderDetails")
public class OrderDetailController extends AbstractController<OrderDetailRepo, OrderDetail, Long> {

	public OrderDetailController(OrderDetailRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
}
