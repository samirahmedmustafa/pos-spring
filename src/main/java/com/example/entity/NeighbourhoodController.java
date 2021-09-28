package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.NeighbourhoodRepo;
import com.example.service.AbstractService;
import com.example.service.NeighbourhoodService;

@RestController
@RequestMapping("neighbourhoods")
public class NeighbourhoodController extends AbstractController<Neighbourhood, Integer> {

	public NeighbourhoodController(NeighbourhoodService service) {
		super(service);
	}
}
