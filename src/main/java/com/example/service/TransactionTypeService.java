package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.TransactionType;
import com.example.exception.ItemNotFoundException;
import com.example.repository.TransactionTypeRepo;

@Service
@Transactional
public class TransactionTypeService {

	private TransactionTypeRepo transactionTypeRepo;

	public TransactionTypeService(TransactionTypeRepo transactionTypeRepo) {
		super();
		this.transactionTypeRepo = transactionTypeRepo;
	}

	public List<TransactionType> getTransactionTypes() {
		return transactionTypeRepo.findAll();
	}

	public TransactionType getTransactionTypeById(Integer id) {
		TransactionType transactionType = transactionTypeRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find Transaction Type with the id %d", id)));
		return transactionType;
	}

	public TransactionType save(TransactionType transactionType) {
		TransactionType savedTransactionType = transactionTypeRepo.save(transactionType);
		return savedTransactionType;
	}

	public TransactionType update(TransactionType transactionType, Integer id) {
		TransactionType existingTransactionType = transactionTypeRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find Transaction Type with the id %d", id)));
		TransactionType updatedTransactionType = updateTransactionType(existingTransactionType, transactionType);
		return transactionTypeRepo.save(updatedTransactionType);
	}

	public void deleteById(Integer id) {
		transactionTypeRepo.deleteById(id);
	}

	private TransactionType updateTransactionType(TransactionType existingTransactionType,
			TransactionType transactionType) {
		existingTransactionType.setName(transactionType.getName());
		existingTransactionType.setPayments(transactionType.getPayments());
		existingTransactionType.setTransactionType(transactionType.getTransactionType());
		return existingTransactionType;
	}

}
