package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.entity.InventoryDetail;
import com.example.entity.Product;
import com.example.exception.ItemNotFoundException;
import com.example.repository.EmployeeRepo;
import com.example.repository.InventoryDetailRepo;

@Service
@Transactional
public class InventoryDetailService extends AbstractService<InventoryDetail, Long> {

	public InventoryDetailService(InventoryDetailRepo repository) {
		super(repository);
	}
}
