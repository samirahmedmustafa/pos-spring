package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryRepo;
import com.example.service.AbstractService;
import com.example.service.InventoryService;

@RestController
@RequestMapping("inventories")
public class InventoryController extends AbstractController<Inventory, Long> {

	public InventoryController(InventoryService service) {
		super(service);
	}

}
