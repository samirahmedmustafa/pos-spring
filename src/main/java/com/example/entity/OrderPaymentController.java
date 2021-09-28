package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.OrderPaymentRepo;
import com.example.service.AbstractService;
import com.example.service.OrderPaymentService;

@RestController
@RequestMapping("orderPayments")
public class OrderPaymentController extends AbstractController<OrderPayment, Long> {

	public OrderPaymentController(OrderPaymentService service) {
		super(service);
	}
}
