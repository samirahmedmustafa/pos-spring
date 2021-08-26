package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CityRepo;

@RestController
@RequestMapping("cities")
public class CityController extends AbstractController<CityRepo, City, Integer> {

	public CityController(CityRepo r) {
		super(r);
	}
}
