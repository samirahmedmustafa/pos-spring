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
import com.example.service.OrderDetailService;

@RestController
@RequestMapping("orderdetails")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping
	public ResponseEntity<List<OrderDetail>> getOrderDetails() {
		List<OrderDetail> orderDetails = orderDetailService.getOrderDetails();
		return new ResponseEntity<List<OrderDetail>>(orderDetails, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable Long id) {
		OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
		return new ResponseEntity<OrderDetail>(orderDetail, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail) {
		OrderDetail savedOrderDetail = orderDetailService.save(orderDetail);
		return new ResponseEntity<OrderDetail>(savedOrderDetail, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<OrderDetail> updateOrderDetail(@RequestBody OrderDetail orderDetail, @PathVariable Long id) {
		OrderDetail updatedOrderDetail = orderDetailService.update(orderDetail, id);
		return new ResponseEntity<OrderDetail>(updatedOrderDetail, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteOrderDetail(@PathVariable Long id) {
		orderDetailService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
