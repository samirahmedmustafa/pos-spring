package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.SupplierRepo;

@RestController
@RequestMapping("suppliers")
public class SupplierController extends AbstractController<SupplierRepo, Supplier, Integer> {

	public SupplierController(SupplierRepo r) {
		super(r);
	}

}
