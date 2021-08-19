package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Inventory;
import com.example.exception.ItemNotFoundException;
import com.example.repository.InventoryRepo;

@Service
@Transactional
public class InventoryService {

	private InventoryRepo inventoryRepo;

	public InventoryService(InventoryRepo inventoryRepo) {
		super();
		this.inventoryRepo = inventoryRepo;
	}

	public List<Inventory> getInventories() {
		return inventoryRepo.findAll();
	}

	public Inventory getInventoryById(Long id) {
		Inventory inventory = inventoryRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Inventory with the id %d", id)));
		return inventory;
	}

	public Inventory save(Inventory inventory) {
		Inventory savedInventory = inventoryRepo.save(inventory);
		return savedInventory;
	}

	public Inventory update(Inventory inventory, Long id) {
		Inventory existingInventory = inventoryRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Inventory with the id %d", id)));
		Inventory updatedInventory = updateInventory(existingInventory, inventory);
		return inventoryRepo.save(updatedInventory);
	}

	public void deleteById(Long id) {
		inventoryRepo.deleteById(id);
	}

	private Inventory updateInventory(Inventory existingInventory, Inventory inventory) {
		existingInventory.setProduct(inventory.getProduct());
		existingInventory.setPurchasePrice(inventory.getPurchasePrice());
		existingInventory.setQuantity(inventory.getQuantity());
		existingInventory.setSellPrice(inventory.getSellPrice());
		return existingInventory;
	}

}
