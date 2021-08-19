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
import com.example.service.StatusService;

@RestController
@RequestMapping("statuses")
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	@GetMapping
	public ResponseEntity<List<Status>> getShippers() {
		List<Status> statuses = statusService.getStatuses();
		return new ResponseEntity<List<Status>>(statuses, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Status> getStatus(@PathVariable Integer id) {
		Status status = statusService.getStatusById(id);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Status> saveStatus(@RequestBody Status status) {
		Status savedStatus = statusService.save(status);
		return new ResponseEntity<Status>(savedStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Status> updateStatus(@RequestBody Status status, @PathVariable Integer id) {
		Status updatedStatus = statusService.update(status, id);
		return new ResponseEntity<Status>(updatedStatus, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updateStatus(@PathVariable Integer id) {
		statusService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
