package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Customer;
import com.example.entity.Expense;
import com.example.entity.ExpenseType;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.ExpenseTypeRepo;

@Service
@Transactional
public class ExpenseTypeService extends AbstractService<ExpenseType, Integer> {

	public ExpenseTypeService(ExpenseTypeRepo repository) {
		super(repository);
	}
}
