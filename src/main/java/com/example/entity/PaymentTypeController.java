package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.PaymentTypeRepo;

@RestController
@RequestMapping("paymenttypes")
public class PaymentTypeController extends AbstractController<PaymentTypeRepo, PaymentType, Integer> {

	public PaymentTypeController(PaymentTypeRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	
}
