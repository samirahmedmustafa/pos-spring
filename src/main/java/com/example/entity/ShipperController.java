package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.ShipperRepo;

@RestController
@RequestMapping("shippers")
public class ShipperController extends AbstractController<ShipperRepo, Shipper, Integer> {

	public ShipperController(ShipperRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
}