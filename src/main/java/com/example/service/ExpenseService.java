package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Customer;
import com.example.entity.Expense;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.ExpenseRepo;

@Service
@Transactional
public class ExpenseService extends AbstractService<Expense, Long> {

	public ExpenseService(ExpenseRepo repository) {
		super(repository);
	}
}
