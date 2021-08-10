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

import com.example.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService OrderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> orders = OrderService.getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Long id) {
		Order order = OrderService.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
		Order savedOrder = OrderService.save(order);
		return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id) {
		Order updatedOrder = OrderService.update(order, id);
		return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updateOrder(@PathVariable Long id) {
		OrderService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
