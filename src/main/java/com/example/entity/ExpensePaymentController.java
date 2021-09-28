package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ExpensePaymentRepo;
import com.example.service.AbstractService;
import com.example.service.ExpensePaymentService;

@RestController
@RequestMapping("expensePayments")
public class ExpensePaymentController extends AbstractController<ExpensePayment, Long> {

	public ExpensePaymentController(ExpensePaymentService service) {
		super(service);
	}	
}
