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

import com.example.service.ShipperService;

@RestController
@RequestMapping("shippers")
public class ShipperController {

	@Autowired
	private ShipperService shipperService;
	
	@GetMapping
	public ResponseEntity<List<Shipper>> getShippers() {
		List<Shipper> shippers = shipperService.getShippers();
		return new ResponseEntity<List<Shipper>>(shippers, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Shipper> getShipper(@PathVariable Integer id) {
		Shipper shipper = shipperService.getShipperById(id);
		return new ResponseEntity<Shipper>(shipper, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Shipper> saveShipper(@RequestBody Shipper shipper) {
		Shipper savedShipper = shipperService.save(shipper);
		return new ResponseEntity<Shipper>(savedShipper, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Shipper> updateShipper(@RequestBody Shipper shipper, @PathVariable Integer id) {
		Shipper updatedShipper = shipperService.update(shipper, id);
		return new ResponseEntity<Shipper>(updatedShipper, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updateShipper(@PathVariable Integer id) {
		shipperService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
