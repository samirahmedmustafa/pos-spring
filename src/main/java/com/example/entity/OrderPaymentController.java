package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderPaymentRepo;

@RestController
@RequestMapping("order_payments")
public class OrderPaymentController extends AbstractController<OrderPaymentRepo, OrderPayment, Long> {

	public OrderPaymentController(OrderPaymentRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
}
