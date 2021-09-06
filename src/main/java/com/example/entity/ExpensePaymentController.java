package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ExpensePaymentRepo;

@RestController
@RequestMapping("expensePayments")
public class ExpensePaymentController extends AbstractController<ExpensePaymentRepo, ExpensePayment, Long> {

	public ExpensePaymentController(ExpensePaymentRepo r) {
		super(r);
	}
	
}
