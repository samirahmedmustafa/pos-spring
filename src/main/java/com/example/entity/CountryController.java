package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CountryRepo;

@RestController
@RequestMapping("countries")
public class CountryController extends AbstractController<CountryRepo, Country, Integer> {

	public CountryController(CountryRepo r) {
		super(r);
	}
}
