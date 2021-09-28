package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.SupplierController;
import com.example.repository.SupplierRepo;

class ControllerTest {

	@Autowired
	private SupplierRepo supplierRepo;
	
	@Test
	void testSupplier() {
//		SupplierController controller = new SupplierController(supplierRepo);
//		System.out.println("testing..************" + controller.findAll());
	}

}
