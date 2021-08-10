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

import com.example.service.SupplierService;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@GetMapping
	public ResponseEntity<List<Supplier>> getSuppliers() {
		List<Supplier> suppliers = supplierService.getSuppliers();
		return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Supplier> getSupplier(@PathVariable Integer id) {
		Supplier supplier = supplierService.getSupplierById(id);
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier) {
		Supplier savedSupplier = supplierService.save(supplier);
		return new ResponseEntity<Supplier>(savedSupplier, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier, @PathVariable Integer id) {
		Supplier updatedSupplier = supplierService.update(supplier, id);
		return new ResponseEntity<Supplier>(updatedSupplier, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updateSupplier(@PathVariable Integer id) {
		supplierService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
