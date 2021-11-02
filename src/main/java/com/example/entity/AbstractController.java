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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exception.DatabaseConstraintException;
import com.example.service.AbstractService;
import com.example.service.CityService;
import com.example.service.CountryService;
import com.example.service.CustomerService;
import com.example.service.EmployeeService;
import com.example.service.InventoryDetailService;
import com.example.service.OrderDetailService;
import com.example.service.ProductService;
import com.example.service.RoleService;

public abstract class AbstractController<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

	private AbstractService<T, ID> service;

	public AbstractController(AbstractService<T, ID> service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		List<T> ts = service.getAll();
		return new ResponseEntity<>(ts, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<T> findById(@PathVariable ID id) {
		T t = service.getById(id);
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@GetMapping("byBarcode")
	public ResponseEntity<Product> findByBarcode(@RequestParam String barcode) {
		Product product = ((ProductService) service).getByBarcode(barcode);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("byName")
	public ResponseEntity<Boolean> findCountryByName(@RequestParam String name) {
		Boolean isExist = ((CountryService) service).isCountryNameExists(name);
		return new ResponseEntity<>(isExist, HttpStatus.OK);
	}

	@GetMapping("byProductName")
	public ResponseEntity<?> findProductByName(@RequestParam String name) {
		try {
			Product product = ((ProductService) service).getProductByName(name);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}

	@GetMapping("byAccountId")
	public ResponseEntity<?> findByAccountId(@RequestParam String accountId) {
		try {
			Employee employee = ((EmployeeService) service).getByAccountId(accountId);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}
	
	@GetMapping("byRoleName")
	public ResponseEntity<?> getByRoleName(@RequestParam String name) {
		try {
			Role role = ((RoleService) service).getByRoleName(name);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}
	
	@GetMapping("byCountryName")
	public ResponseEntity<?> getCitiesByCountry(@RequestParam String name) {
		try {
			List<City> cities = ((CityService) service).getCitiesByCountry(name);
			return new ResponseEntity<>(cities, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Employee employee) {

		try {
			Employee loggedInemployee = ((EmployeeService) service).login(employee);
			return new ResponseEntity<>(loggedInemployee, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			logger.error("login DatabaseConstraintException: " + e);
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("login Exception: " + e);
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}

	@GetMapping("byEmail")
	public ResponseEntity<?> findByEmail(@RequestParam String email) {
		try {
			Employee employee = ((EmployeeService) service).getByEmail(email);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}

	@GetMapping("byPhone")
	public ResponseEntity<Customer> findByPhone(@RequestParam String phone) {
		Customer customer = ((CustomerService) service).getByPhone(phone);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("byInventory")
	public ResponseEntity<List<InventoryDetail>> getByInventoryDetails(@RequestParam Long inventory) {
		List<InventoryDetail> inventoryDetails = ((InventoryDetailService) service).getByInventory(inventory);
		return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
	}

	@GetMapping("byOrder")
	public ResponseEntity<?> findByOrder(@RequestParam Long orderNo) {
		List<OrderDetail> orderDetails = ((OrderDetailService) service).getByOrder(orderNo);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody T t) {
		try {
			T saved = service.save(t);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody T t, @PathVariable ID id) {
		try {
			T updated = service.update(t, id);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable ID id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (DatabaseConstraintException e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}

}
