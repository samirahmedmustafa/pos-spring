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
import com.example.service.TransactionTypeService;

@RestController
@RequestMapping("transactiontypes")
public class TransactionTypeController {

	@Autowired
	private TransactionTypeService transactionTypeService;
	
	@GetMapping
	public ResponseEntity<List<TransactionType>> getTransactionTypes() {
		List<TransactionType> transactionTypes = transactionTypeService.getTransactionTypes();
		return new ResponseEntity<List<TransactionType>>(transactionTypes, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TransactionType> getSupplier(@PathVariable Integer id) {
		TransactionType transactionType = transactionTypeService.getTransactionTypeById(id);
		return new ResponseEntity<TransactionType>(transactionType, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<TransactionType> saveSupplier(@RequestBody TransactionType transactionType) {
		TransactionType savedTransactionType = transactionTypeService.save(transactionType);
		return new ResponseEntity<TransactionType>(savedTransactionType, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TransactionType> update(@RequestBody TransactionType transactionType, @PathVariable Integer id) {
		TransactionType updatedTransactionType = transactionTypeService.update(transactionType, id);
		return new ResponseEntity<TransactionType>(updatedTransactionType, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		transactionTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
