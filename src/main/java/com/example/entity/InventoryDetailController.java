package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.InventoryDetailRepo;
import com.example.service.AbstractService;
import com.example.service.InventoryDetailService;

@RestController
@RequestMapping("inventoryDetails")
public class InventoryDetailController extends AbstractController<InventoryDetail, Long> {

	public InventoryDetailController(InventoryDetailService service) {
		super(service);
	}

}
