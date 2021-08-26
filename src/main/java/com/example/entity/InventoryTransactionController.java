package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryTransactionRepo;

@RestController
@RequestMapping("inventorytransactions")
public class InventoryTransactionController
		extends AbstractController<InventoryTransactionRepo, InventoryTransaction, Long> {

	public InventoryTransactionController(InventoryTransactionRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
