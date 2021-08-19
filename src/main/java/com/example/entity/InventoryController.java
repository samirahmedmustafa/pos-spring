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
import com.example.service.InventoryService;

@RestController
@RequestMapping("inventories")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping
	public ResponseEntity<List<Inventory>> getInventories() {
		List<Inventory> inventories = inventoryService.getInventories();
		return new ResponseEntity<List<Inventory>>(inventories, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Inventory> getInventory(@PathVariable Long id) {
		Inventory inventory = inventoryService.getInventoryById(id);
		return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory inventory) {
		Inventory savedInventory = inventoryService.save(inventory);
		return new ResponseEntity<Inventory>(savedInventory, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory, @PathVariable Long id) {
		Inventory updatedInventory = inventoryService.update(inventory, id);
		return new ResponseEntity<Inventory>(updatedInventory, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
		inventoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
