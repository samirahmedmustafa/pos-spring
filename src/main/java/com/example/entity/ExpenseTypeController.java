package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ExpenseTypeRepo;
import com.example.service.AbstractService;
import com.example.service.ExpenseTypeService;

@RestController
@RequestMapping("expenseTypes")
public class ExpenseTypeController extends AbstractController<ExpenseType, Integer> {
	
	public ExpenseTypeController(ExpenseTypeService service) {
		super(service);
	}
}
