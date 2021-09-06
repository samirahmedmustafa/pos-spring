package com.example.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	public ResponseEntity<List<City>> findAll() {
		List<City> cities = cityService.getAll();
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<City> getById(@PathVariable Integer id) {
		City city = cityService.getById(id);
		return new ResponseEntity<>(city, HttpStatus.OK);
	}
	
	@GetMapping("city")
	public ResponseEntity<List<City>> getCitiesByCountry(@RequestParam(defaultValue = "None", required = false, value = "country") String country) {
		List<City> cities = cityService.getCitiesByCountry(country);
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<City> save(@RequestBody City city) {
		City saved = cityService.save(city);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<City> update(@RequestBody City city, @PathVariable Integer id) {
		City updated = cityService.update(city, id);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		cityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
