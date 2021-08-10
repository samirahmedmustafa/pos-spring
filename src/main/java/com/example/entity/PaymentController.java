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

import com.example.service.PaymentService;

@RestController
@RequestMapping("payments")
public class PaymentController {

	@Autowired
	private PaymentService PaymentService;
	
	@GetMapping
	public ResponseEntity<List<Payment>> getPayments() {
		System.out.println("get Payments");
		List<Payment> payments = PaymentService.getPayments();
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
		Payment payment = PaymentService.getPaymentById(id);
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
		Payment savedPayment = PaymentService.save(payment);
		return new ResponseEntity<Payment>(savedPayment, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @PathVariable Long id) {
		Payment updatedPayment = PaymentService.update(payment, id);
		return new ResponseEntity<Payment>(updatedPayment, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updatePayment(@PathVariable Long id) {
		PaymentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
