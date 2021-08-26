package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryRepo;

@RestController
@RequestMapping("inventories")
public class InventoryController extends AbstractController<InventoryRepo, Inventory, Long> {

	public InventoryController(InventoryRepo r) {
		super(r);
	}

}
