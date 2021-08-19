package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.PaymentType;
import com.example.exception.ItemNotFoundException;
import com.example.repository.PaymentTypeRepo;

@Service
@Transactional
public class PaymentTypeService {

	private PaymentTypeRepo paymentTypeRepo;

	public PaymentTypeService(PaymentTypeRepo paymentTypeRepo) {
		super();
		this.paymentTypeRepo = paymentTypeRepo;
	}

	public List<PaymentType> findAll() {
		return paymentTypeRepo.findAll();
	}

	public PaymentType findById(Integer id) {
		PaymentType paymentType = paymentTypeRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find Payment Type with the id %d", id)));
		return paymentType;
	}

	public PaymentType save(PaymentType paymentType) {
		PaymentType savedPaymentType = paymentTypeRepo.save(paymentType);
		return savedPaymentType;
	}

	public PaymentType update(PaymentType payment, Integer id) {
		PaymentType existingPayment = paymentTypeRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find Payment with the id %d", id)));
		PaymentType updatedPayment = update(existingPayment, payment);
		return paymentTypeRepo.save(updatedPayment);
	}

	public void deleteById(Integer id) {
		paymentTypeRepo.deleteById(id);
	}

	private PaymentType update(PaymentType existingPaymentType, PaymentType paymentType) {
		existingPaymentType.setName(paymentType.getName());
		existingPaymentType.setPayments(paymentType.getPayments());
		return existingPaymentType;
	}

}
