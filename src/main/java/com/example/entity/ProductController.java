package com.example.entity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateCountryException;
import com.example.exception.ExceptionHandling;
import com.example.service.PosService;
import com.example.service.ProductService;
import com.example.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = { "/", "/api/products" })
public class ProductController extends PosController<Product, Long> {

	private final ProductService service;

	public ProductController(ProductService service) {
		super(service);
		this.service = service;
	}

	@GetMapping("byBarcode")
	public ResponseEntity<Product> findByBarcode(@RequestParam String barcode) {
		Product product = service.findByBarcode(barcode);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("byProductName")
	public ResponseEntity<Product> findProductByName(@RequestParam String name) {
		Product product = service.findProductByName(name);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
