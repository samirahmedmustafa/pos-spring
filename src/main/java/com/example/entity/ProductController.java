package com.example.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		System.out.println("get products");
		List<Product> products = productService.getProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product savedProduct = productService.save(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
		Product updatedProduct = productService.update(product, id);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
