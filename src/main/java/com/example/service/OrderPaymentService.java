package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Customer;
import com.example.entity.Expense;
import com.example.entity.ExpensePayment;
import com.example.entity.OrderPayment;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.OrderPaymentRepo;

@Service
@Transactional
public class OrderPaymentService extends AbstractService<OrderPayment, Long> {

	public OrderPaymentService(OrderPaymentRepo repository) {
		super(repository);
	}
}
