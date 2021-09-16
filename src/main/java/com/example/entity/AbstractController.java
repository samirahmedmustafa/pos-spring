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
import com.example.exception.ItemNotFoundException;

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
		T t = r.findById(id).get();
		return new ResponseEntity<>(t, HttpStatus.OK);
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
		r.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Delete error: Item %s not found", id.toString())));
		r.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
