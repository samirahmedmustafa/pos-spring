package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryPaymentRepo;
import com.example.service.AbstractService;
import com.example.service.InventoryPaymentService;

@RestController
@RequestMapping("inventoryPayments")
public class InventoryPaymentController extends AbstractController<InventoryPayment, Long> {

	public InventoryPaymentController(InventoryPaymentService service) {
		super(service);
	}

}
