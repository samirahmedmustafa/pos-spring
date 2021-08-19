package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.InventoryTransaction;
import com.example.exception.ItemNotFoundException;
import com.example.repository.InventoryTransactionRepo;

@Service
@Transactional
public class InventoryTransactionService {

	private InventoryTransactionRepo inventoryTransactionRepo;

	public InventoryTransactionService(InventoryTransactionRepo inventoryTransactionRepo) {
		super();
		this.inventoryTransactionRepo = inventoryTransactionRepo;
	}

	public List<InventoryTransaction> getInventoryTransactions() {
		return inventoryTransactionRepo.findAll();
	}

	public InventoryTransaction getInventoryTransactionById(Long id) {
		InventoryTransaction inventoryTransaction = inventoryTransactionRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(
						String.format("Couldn't find Inventory transaction with the id %d", id)));
		return inventoryTransaction;
	}

	public InventoryTransaction save(InventoryTransaction inventoryTransaction) {
		InventoryTransaction savedInventoryTransaction = inventoryTransactionRepo.save(inventoryTransaction);
		return savedInventoryTransaction;
	}

	public InventoryTransaction update(InventoryTransaction inventoryTransaction, Long id) {
		InventoryTransaction existingInventoryTransaction = inventoryTransactionRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(
						String.format("Couldn't find Inventory transaction with the id %d", id)));
		InventoryTransaction updatedInventoryTransaction = updateInventoryTransaction(existingInventoryTransaction,
				inventoryTransaction);
		return inventoryTransactionRepo.save(updatedInventoryTransaction);
	}

	public void deleteById(Long id) {
		inventoryTransactionRepo.deleteById(id);
	}

	private InventoryTransaction updateInventoryTransaction(InventoryTransaction existingInventoryTransaction,
			InventoryTransaction inventoryTransaction) {
		existingInventoryTransaction.setProduct(inventoryTransaction.getProduct());
		existingInventoryTransaction.setQuantity(inventoryTransaction.getQuantity());
		existingInventoryTransaction.setTransactionType(inventoryTransaction.getTransactionType());
		return existingInventoryTransaction;
	}

}
