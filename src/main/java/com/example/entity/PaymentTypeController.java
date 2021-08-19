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
import com.example.service.PaymentTypeService;

@RestController
@RequestMapping("paymenttypes")
public class PaymentTypeController {

	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@GetMapping
	public ResponseEntity<List<PaymentType>> findAll() {
		List<PaymentType> paymentTypes = paymentTypeService.findAll();
		return new ResponseEntity<List<PaymentType>>(paymentTypes, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PaymentType> findById(@PathVariable Integer id) {
		PaymentType paymentType = paymentTypeService.findById(id);
		return new ResponseEntity<PaymentType>(paymentType, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<PaymentType> save(@RequestBody PaymentType paymentType) {
		PaymentType savedPaymentType = paymentTypeService.save(paymentType);
		return new ResponseEntity<PaymentType>(savedPaymentType, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<PaymentType> update(@RequestBody PaymentType paymentType, @PathVariable Integer id) {
		PaymentType updatedPaymentType = paymentTypeService.update(paymentType, id);
		return new ResponseEntity<PaymentType>(updatedPaymentType, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		paymentTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
