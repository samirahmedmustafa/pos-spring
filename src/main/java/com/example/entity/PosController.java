package com.example.entity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateCountryException;
import com.example.exception.ExceptionHandling;
import com.example.service.PosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PosController<T, ID> extends ExceptionHandling {

	private final PosService<T, ID> service;

	public PosController(PosService<T, ID> service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> findItems() {
		List<T> t = service.findAll();
		log.info("users: {}", t);
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<T> findById(@PathVariable ID id) {
		T t = service.findById(id);
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<T> update(@RequestBody T t) {
		T updated = service.update(t);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody T t) {
		T saved = service.save(t);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable ID id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@GetMapping("countries/byName")
//	public ResponseEntity<Country> findCountryByName(@RequestParam String name) throws DuplicateCountryException {
//		Country country = service.findCountryByName(name);
//		return new ResponseEntity<>(country, HttpStatus.OK);
//	}
//
//	@GetMapping("products/byProductName")
//	public ResponseEntity<Product> findProductByName(@RequestParam String name) {
//		Product product = service.findProductByName(name);
//		return new ResponseEntity<>(product, HttpStatus.OK);
//	}
//
//	@GetMapping("users/byUsername")
//	public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
//		User user = posService.findUserByUsername(username);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
//
//	@GetMapping("roles/byRoleName")
//	public ResponseEntity<Role> getByRoleName(@RequestParam String name) {
//		Role role = posService.findByRoleName(name);
//		return new ResponseEntity<>(role, HttpStatus.OK);
//	}
//
//	@GetMapping("cities/byCountryName")
//	public ResponseEntity<List<City>> getCitiesByCountry(@RequestParam String name) {
//		List<City> cities = posService.findCitiesByCountry(name);
//		return new ResponseEntity<>(cities, HttpStatus.OK);
//	}
//
//	@GetMapping("users/byEmail")
//	public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
//		User user = posService.findUserByEmail(email);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
//
//	@GetMapping("customers/byPhone")
//	public ResponseEntity<Customer> findByPhone(@RequestParam String phone) {
//		Customer customer = posService.findCustomerByPhone(phone);
//		return new ResponseEntity<>(customer, HttpStatus.OK);
//	}
//
//	@GetMapping("inventoryDetails/byInventory")
//	public ResponseEntity<List<InventoryDetail>> getByInventoryDetails(@RequestParam Long inventoryId) {
//		List<InventoryDetail> inventoryDetails = posService.findByInventory(inventoryId);
//		return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
//	}
//
//	@GetMapping("orderDetails/byOrder")
//	public ResponseEntity<List<OrderDetail>> findByOrder(@RequestParam Long orderNo) {
//		List<OrderDetail> orderDetails = posService.getByOrder(orderNo);
//		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//	}
//
//	@PostMapping("countries")
//	public ResponseEntity<Country> saveCountry(@RequestBody Country country) {
//		Country saved = posService.saveCountry(country);
//		return new ResponseEntity<Country>(saved, HttpStatus.CREATED);
//	}

}
