package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryDetailRepo;

@RestController
@RequestMapping("inventorydetails")
public class InventoryDetailController extends AbstractController<InventoryDetailRepo, InventoryDetail, Long> {

	public InventoryDetailController(InventoryDetailRepo r) {
		super(r);
	}

}
