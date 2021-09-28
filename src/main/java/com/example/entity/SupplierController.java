package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.SupplierRepo;
import com.example.service.AbstractService;
import com.example.service.SupplierService;

@RestController
@RequestMapping("suppliers")
public class SupplierController extends AbstractController<Supplier, Integer> {

	public SupplierController(SupplierService service) {
		super(service);
	}

}
