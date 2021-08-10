package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Payment;
import com.example.exception.ItemNotFoundException;
import com.example.repository.PaymentRepo;

@Service
@Transactional
public class PaymentService {

	private PaymentRepo paymentRepo;

	public PaymentService(PaymentRepo paymentRepo) {
		super();
		this.paymentRepo = paymentRepo;
	}

	public List<Payment> getPayments() {
		return paymentRepo.findAll();
	}

	public Payment getPaymentById(Long id) {
		Payment payment = paymentRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find payment with the id %d", id)));
		return payment;
	}

	public Payment save(Payment payment) {
		Payment savedPayment = paymentRepo.save(payment);
		return savedPayment;
	}

	public Payment update(Payment payment, Long id) {
		Payment existingPayment = paymentRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find Payment with the id %d", id)));
		Payment updatedPayment = updatePayment(existingPayment, payment);
		return paymentRepo.save(updatedPayment);
	}

	public void deleteById(Long id) {
		paymentRepo.deleteById(id);
	}

	private Payment updatePayment(Payment existingPayment, Payment payment) {
		existingPayment.setAmount(payment.getAmount());
		existingPayment.setOrder(payment.getOrder());
		existingPayment.setPaymentDate(payment.getPaymentDate());
		return existingPayment;
	}

}
