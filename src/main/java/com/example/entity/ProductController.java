package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ProductRepo;

@RestController
@RequestMapping("products")
public class ProductController extends AbstractController<ProductRepo, Product, Long> {

	public ProductController(ProductRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
}