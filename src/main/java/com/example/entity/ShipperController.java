package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ShipperRepo;
import com.example.service.AbstractService;
import com.example.service.ShipperService;

@RestController
@RequestMapping("shippers")
public class ShipperController extends AbstractController<Shipper, Integer> {

	public ShipperController(ShipperService service) {
		super(service);
	}
}