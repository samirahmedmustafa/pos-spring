package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryPaymentRepo;

@RestController
@RequestMapping("inventoryPayments")
public class InventoryPaymentController extends AbstractController<InventoryPaymentRepo, InventoryPayment, Long> {

	public InventoryPaymentController(InventoryPaymentRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
