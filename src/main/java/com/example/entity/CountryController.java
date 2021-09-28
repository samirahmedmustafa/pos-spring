package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CountryRepo;
import com.example.service.AbstractService;
import com.example.service.CountryService;

@RestController
@RequestMapping("countries")
public class CountryController extends AbstractController<Country, Integer> {

	public CountryController(CountryService service) {
		super(service);
	}
}
