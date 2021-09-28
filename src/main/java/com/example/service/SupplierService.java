package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Address;
import com.example.entity.Category;
import com.example.entity.Customer;
import com.example.entity.Supplier;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.SupplierRepo;

@Service
@Transactional
public class SupplierService extends AbstractService<Supplier, Integer> {

	public SupplierService(SupplierRepo repository) {
		super(repository);
	}
}
