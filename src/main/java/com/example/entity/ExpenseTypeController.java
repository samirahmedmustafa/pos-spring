package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ExpenseTypeRepo;

@RestController
@RequestMapping("expenseTypes")
public class ExpenseTypeController extends AbstractController<ExpenseTypeRepo, ExpenseType, Integer> {

	public ExpenseTypeController(ExpenseTypeRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
