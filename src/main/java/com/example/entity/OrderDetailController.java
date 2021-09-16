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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.ItemNotFoundException;
import com.example.repository.OrderDetailRepo;

@RestController
@RequestMapping("orderDetails")
public class OrderDetailController {

	@Autowired
	private OrderDetailRepo orderDetailRepo;
	
	@GetMapping
	public ResponseEntity<List<OrderDetail>> findAll() {
		List<OrderDetail> orderDetails = orderDetailRepo.findAll();
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}
	
	@GetMapping("byOrder")
	public ResponseEntity<List<OrderDetail>> findByOrder(@RequestParam Long orderNo) {
		List<OrderDetail> orderDetails = orderDetailRepo
				.findByOrder(orderNo).orElseThrow(() -> new ItemNotFoundException(String.format("Invalid order No. %d", orderNo)));
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<OrderDetail> findById(@PathVariable Long id) {
		OrderDetail orderDetail = orderDetailRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Find error: Item %s not found", id.toString())));
		return new ResponseEntity<>(orderDetail, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderDetail> save(@RequestBody OrderDetail orderDetail) {
		OrderDetail savedOrderDetail = orderDetailRepo.save(orderDetail);
		return new ResponseEntity<>(savedOrderDetail, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<OrderDetail> update(@RequestBody OrderDetail orderDetail, @PathVariable Long id) {
		orderDetailRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Update error: Item %s not found", id.toString())));
		OrderDetail updatedOrderDetail = orderDetailRepo.save(orderDetail);
		return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		orderDetailRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Delete error: Item %s not found", id.toString())));
		orderDetailRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
