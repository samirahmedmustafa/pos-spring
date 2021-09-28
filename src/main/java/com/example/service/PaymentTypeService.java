package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.entity.Inventory;
import com.example.entity.PaymentType;
import com.example.entity.Product;
import com.example.exception.ItemNotFoundException;
import com.example.repository.EmployeeRepo;
import com.example.repository.PaymentTypeRepo;

@Service
@Transactional
public class PaymentTypeService extends AbstractService<PaymentType, Integer> {

	public PaymentTypeService(PaymentTypeRepo repository) {
		super(repository);
	}
}
