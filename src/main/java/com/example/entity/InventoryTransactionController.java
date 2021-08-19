package com.example.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.InventoryTransactionService;

@RestController
@RequestMapping("inventorytransactions")
public class InventoryTransactionController {

	@Autowired
	private InventoryTransactionService inventoryTransactionService;
	
	@GetMapping
	public ResponseEntity<List<InventoryTransaction>> getInventoryTransactions() {
		List<InventoryTransaction> inventoryTransactions = inventoryTransactionService.getInventoryTransactions();
		return new ResponseEntity<List<InventoryTransaction>>(inventoryTransactions, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<InventoryTransaction> getInventoryTransaction(@PathVariable Long id) {
		InventoryTransaction inventoryTransaction = inventoryTransactionService.getInventoryTransactionById(id);
		return new ResponseEntity<InventoryTransaction>(inventoryTransaction, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<InventoryTransaction> saveInventoryTransaction(@RequestBody InventoryTransaction inventoryTransaction) {
		InventoryTransaction savedInventoryTransaction = inventoryTransactionService.save(inventoryTransaction);
		return new ResponseEntity<InventoryTransaction>(savedInventoryTransaction, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<InventoryTransaction> updateInventoryTransaction(@RequestBody InventoryTransaction inventoryTransaction, @PathVariable Long id) {
		InventoryTransaction updatedInventoryTransaction = inventoryTransactionService.update(inventoryTransaction, id);
		return new ResponseEntity<InventoryTransaction>(updatedInventoryTransaction, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteInventoryTransaction(@PathVariable Long id) {
		inventoryTransactionService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
