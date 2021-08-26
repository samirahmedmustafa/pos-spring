package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.NeighbourhoodRepo;

@RestController
@RequestMapping("neighbourhoods")
public class NeighbourhoodController extends AbstractController<NeighbourhoodRepo, Neighbourhood, Integer> {

	public NeighbourhoodController(NeighbourhoodRepo r) {
		super(r);
	}

}
