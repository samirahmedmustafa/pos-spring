package com.example.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.exception.BusinessException;
import com.example.exception.ItemNotFoundException;
import com.example.repository.InventoryDetailRepo;
import com.example.repository.OrderDetailRepo;
import com.example.repository.ProductRepo;
import com.example.service.AbstractService;
import com.example.service.InventoryDetailService;
import com.example.service.OrderDetailService;
import com.example.service.ProductService;

public abstract class AbstractController<T, ID> {

	private AbstractService<T, ID> service;

	public AbstractController(AbstractService<T, ID> service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		List<T> ts = service.getAll();
		return new ResponseEntity<>(ts, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<T> findById(@PathVariable ID id) {
		T t = service.getById(id);
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@GetMapping("byBarcode")
	public ResponseEntity<Product> findByBarcode(@RequestParam String barcode) {
		Product product = ((ProductService) service).getByBarcode(barcode);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("byInventory")
	public ResponseEntity<List<InventoryDetail>> getByInventory(@RequestParam Long inventory) {
		List<InventoryDetail> inventoryDetails = ((InventoryDetailService) service).getByInventory(inventory);
		return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
	}

	@GetMapping("byOrder")
	public ResponseEntity<List<OrderDetail>> findByOrder(@RequestParam Long orderNo) {
		List<OrderDetail> orderDetails = ((OrderDetailService) service).getByOrder(orderNo);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<T> save(@RequestBody T t) {
		T savedT = service.save(t);
		return new ResponseEntity<>(savedT, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<T> update(@RequestBody T t, @PathVariable ID id) {
		T updatedT = service.save(t);
		return new ResponseEntity<>(updatedT, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable ID id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
