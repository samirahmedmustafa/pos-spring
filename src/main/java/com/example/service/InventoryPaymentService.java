package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.entity.Inventory;
import com.example.entity.InventoryPayment;
import com.example.entity.Product;
import com.example.exception.ItemNotFoundException;
import com.example.repository.EmployeeRepo;
import com.example.repository.InventoryPaymentRepo;

@Service
@Transactional
public class InventoryPaymentService extends AbstractService<InventoryPayment, Long> {

	public InventoryPaymentService(InventoryPaymentRepo repository) {
		super(repository);
	}
}