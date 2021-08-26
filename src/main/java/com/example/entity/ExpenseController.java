package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ExpenseRepo;

@RestController
@RequestMapping("expenses")
public class ExpenseController extends AbstractController<ExpenseRepo, Expense, Long> {

	public ExpenseController(ExpenseRepo r) {
		super(r);
	}
	
}
