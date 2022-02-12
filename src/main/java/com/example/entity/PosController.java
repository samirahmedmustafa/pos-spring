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

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = { "/", "/api" })
public abstract class PosController extends ExceptionHandling {

	private final PosService posService;

	@GetMapping("users")
	public ResponseEntity<List<User>> findUsers() {
		List<User> users = posService.findUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		User user = posService.findUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("products/byBarcode")
	public ResponseEntity<Product> findByBarcode(@RequestParam String barcode) {
		Product product = posService.getByBarcode(barcode);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("countries/byName")
	public ResponseEntity<Country> findCountryByName(@RequestParam String name) throws DuplicateCountryException {
		Country country = posService.findCountryByName(name);
		return new ResponseEntity<>(country, HttpStatus.OK);
	}

	@GetMapping("products/byProductName")
	public ResponseEntity<Product> findProductByName(@RequestParam String name) {
		Product product = posService.findProductByName(name);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("users/byUsername")
	public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
		User user = posService.findUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("roles/byRoleName")
	public ResponseEntity<Role> getByRoleName(@RequestParam String name) {
		Role role = posService.findByRoleName(name);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	@GetMapping("cities/byCountryName")
	public ResponseEntity<List<City>> getCitiesByCountry(@RequestParam String name) {
		List<City> cities = posService.findCitiesByCountry(name);
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}

	@GetMapping("users/byEmail")
	public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
		User user = posService.findUserByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("customers/byPhone")
	public ResponseEntity<Customer> findByPhone(@RequestParam String phone) {
		Customer customer = posService.findCustomerByPhone(phone);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("inventoryDetails/byInventory")
	public ResponseEntity<List<InventoryDetail>> getByInventoryDetails(@RequestParam Long inventoryId) {
		List<InventoryDetail> inventoryDetails = posService.findByInventory(inventoryId);
		return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
	}

	@GetMapping("orderDetails/byOrder")
	public ResponseEntity<List<OrderDetail>> findByOrder(@RequestParam Long orderNo) {
		List<OrderDetail> orderDetails = posService.getByOrder(orderNo);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

	@PostMapping("countries")
	public ResponseEntity<Country> saveCountry(@RequestBody Country country) {
		Country saved = posService.saveCountry(country);
		return new ResponseEntity<Country>(saved, HttpStatus.CREATED);
	}

	@PutMapping("countries/{id}")
	public ResponseEntity<?> updateCountry(@RequestBody Country country) {
		Country updated = posService.updateCountry(country);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCountryById(@PathVariable Integer id) {
		posService.deleteCountryById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
