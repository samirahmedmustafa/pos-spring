package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.TransactionTypeRepo;

@RestController
@RequestMapping("transactiontypes")
public class TransactionTypeController extends AbstractController<TransactionTypeRepo, TransactionType, Integer> {

	public TransactionTypeController(TransactionTypeRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
}