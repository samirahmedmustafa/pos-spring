package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ExpenseRepo;
import com.example.service.AbstractService;
import com.example.service.ExpenseService;

@RestController
@RequestMapping("expenses")
public class ExpenseController extends AbstractController<Expense, Long> {

	public ExpenseController(ExpenseService service) {
		super(service);
	}	
}
