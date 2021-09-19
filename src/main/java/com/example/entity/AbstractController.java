package com.example.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.exception.BusinessException;
import com.example.exception.ItemNotFoundException;
import com.example.repository.ProductRepo;

public abstract class AbstractController<R extends JpaRepository<T, ID>, T, ID> {

	private R r;

	public AbstractController(R r) {
		super();
		this.r = r;
	}

	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		List<T> ts = r.findAll();
		return new ResponseEntity<>(ts, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<T> findById(@PathVariable ID id) {
		T t = r.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Invalid item id")));
		return new ResponseEntity<>(t, HttpStatus.OK);
	}
	
	@GetMapping("byBarcode")
	public ResponseEntity<Product> findByBarcode(@RequestParam String barcode) {
		Product product = ((ProductRepo)r).getByBarcode(barcode).orElseThrow(() -> new ItemNotFoundException(String.format("Invalid item barcode")));
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<T> save(@RequestBody T t) {
		T savedT = r.save(t);
		return new ResponseEntity<>(savedT, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<T> update(@RequestBody T t, @PathVariable ID id) {
		T updatedT = r.save(t);
		return new ResponseEntity<>(updatedT, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable ID id) {
		r.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Deletion error, invalid id: ", id)));
		r.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
