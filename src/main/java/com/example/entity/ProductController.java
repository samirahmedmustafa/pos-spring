package com.example.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ProductRepo;
import com.example.service.AbstractService;
import com.example.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController extends AbstractController<Product, Long> {
	
	public ProductController(ProductService service) {
		super(service);
	}
}