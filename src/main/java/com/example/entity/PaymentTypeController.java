package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.PaymentTypeRepo;
import com.example.service.AbstractService;
import com.example.service.PaymentTypeService;

@RestController
@RequestMapping("paymentTypes")
public class PaymentTypeController extends AbstractController<PaymentType, Integer> {

	public PaymentTypeController(PaymentTypeService service) {
		super(service);
	}	
}
