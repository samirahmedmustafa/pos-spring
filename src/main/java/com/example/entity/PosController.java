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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PosController<T, ID> extends ExceptionHandling {

	private final PosService<T, ID> service;

	public PosController(PosService<T, ID> service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> findItems() {
		List<T> t = service.findAll();
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<T> findById(@PathVariable ID id) {
		T t = service.findById(id);
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<T> update(@RequestBody T t) {
		T updated = service.update(t);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody T t) {
		T saved = service.save(t);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable ID id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
