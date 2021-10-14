package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.AbstractController;
import com.example.entity.City;
import com.example.entity.Country;
import com.example.entity.Customer;
import com.example.entity.Employee;
import com.example.entity.Expense;
import com.example.entity.InventoryDetail;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;
import com.example.repository.CountryRepo;
import com.example.repository.CustomerRepo;
import com.example.repository.EmployeeRepo;
import com.example.repository.InventoryDetailRepo;
import com.example.repository.OrderDetailRepo;
import com.example.repository.ProductRepo;

public class AbstractService<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

	private JpaRepository<T, ID> repository;

	public AbstractService(JpaRepository<T, ID> repository) {
		super();
		this.repository = repository;
	}

	public List<T> getAll() {
		return repository.findAll();
	}

	public T getById(ID id) {

		T t = repository.findById(id).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Couldn't find item with the id %s", id)).build());
		return t;
	}

	public Employee getByAccountId(String accountId) {
		return ((EmployeeRepo) repository).getByAccountId(accountId).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Account id %s not found", accountId)).build());
	}

	public Employee getByEmail(String email) {
		return ((EmployeeRepo) repository).getByEmail(email).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Email %s not found", email)).build());
	}
	
	public Product getProductByName(String name) {
		return ((ProductRepo) repository).getByName(name).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Invalid Product name %s", name)).build());
	}
	
	public Customer getByPhone(String phone) {
		return ((CustomerRepo) repository).getByPhone(phone).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("No record with the phone %s", phone)).build());
	}

	public List<OrderDetail> getByOrder(Long orderNo) {

		List<OrderDetail> orderDetails = ((OrderDetailRepo) repository).getByOrder(orderNo).get();
		return orderDetails;
	}

	public List<InventoryDetail> getByInventory(Long inventory) {
		List<InventoryDetail> inventoryDetails = ((InventoryDetailRepo) repository).getByInventory(inventory)
				.orElseThrow(() -> DatabaseConstraintException.builder()
						.message(String.format("No inventory details with the inventory id %s", inventory)).build());
		return inventoryDetails;
	}

	public Product getByBarcode(String barcode) {
		Product product = ((ProductRepo) repository).getByBarcode(barcode).orElseThrow(() -> DatabaseConstraintException
				.builder().message(String.format("Couldn't find item with the barcode %s", barcode)).build());
		return product;
	}

	public Boolean isCountryNameExists(String name) {
		Boolean isExist = ((CountryRepo) repository).getCountryByName(name).isPresent();

		if (isExist)
			return true;
//			throw DatabaseConstraintException.builder().message(String.format("Country is already exists %s", name))
//					.build();

		return false;
	}

	public Product debitProduct(Long id, Integer quantity) {
		Product product = ((ProductRepo) repository).getById(id);
		product.setQuantity(product.getQuantity() + quantity);
		product = ((ProductRepo) repository).save(product);
		return product;
	}

	public List<City> getCitiesByCountry(String country) {
		List<City> cities = ((CityRepo) repository).findCitiesByCountry(country)
				.orElseThrow(() -> DatabaseConstraintException.builder()
						.message(String.format("Couldn't find cities in the country %s", country)).build());
		return cities;
	}

	public Country getCountryByCode(String code) {
		Country country = ((CountryRepo) repository).getCountryByCode(code).get();
		return country;
	}

//	public Country getCountryByName(String name) {
//		Country country = ((CountryRepo) repository).getCountryByName(name).get();
//		return country;
//	}

	public Boolean isCountryCodeExist(String code) {
		Boolean isCodeExist = ((CountryRepo) repository).getCountryByCode(code).isPresent();

		if (isCodeExist)
			throw DatabaseConstraintException.builder()
					.message(String.format("Country code %s already exists in the database", (code))).build();

		return true;
	}

	public Boolean isCountryNameExist(String name) {
		Boolean isNameExist = ((CountryRepo) repository).getCountryByName(name).isPresent();
		if (isNameExist)
			throw DatabaseConstraintException.builder()
					.message(String.format("Country name %s already exists in the database", (name))).build();

		return true;
	}

	public Boolean isEmployeeAccountIdExist(String accountId) {

		Boolean accountIdIsExist = ((EmployeeRepo) repository).getByAccountId(accountId).isPresent();

		if (accountIdIsExist)
			throw DatabaseConstraintException.builder()
					.message(String.format("Employee account Id %s already exists in the database", (accountId)))
					.build();

		return true;
	}

	public Boolean isEmailValid(T t) {
		Boolean emailIsExist = ((EmployeeRepo) repository).getByEmail(((Employee) t).getEmail()).isPresent();

		if (emailIsExist)
			throw DatabaseConstraintException.builder().message(
					String.format("Employee account Id %s already exists in the database", ((Employee) t).getEmail()))
					.build();

		return true;
	}

	private String encryptPassword(T t) {
		return (new BCryptPasswordEncoder().encode((((Employee) t).getPassword())));
	}

	public void validateCustomerPhone(T t) {
		Boolean isExist = ((CustomerRepo) repository).getByPhone(((Customer) t).getPhone()).isPresent();

		if (isExist)
			throw DatabaseConstraintException.builder()
					.message(String.format("Phone no. %s already exists in the database", ((Customer) t).getPhone()))
					.build();
	}

	public void isPasswordValid(String password) {

		if (password == null || password.replaceAll("\\s+", "").length() == 0)
			throw DatabaseConstraintException.builder().message(String.format("Employee password cannot be null"))
					.build();
	}

	public T save(T t) {

		logger.error("repository.findById(id): " + t);

		if (t instanceof Country) {
			isCountryCodeExist(((Country) t).getCode());
			isCountryNameExist(((Country) t).getName());
		}

		if (t instanceof Employee) {
			isEmployeeAccountIdExist((((Employee) t).getAccountId()));
			isPasswordValid((((Employee) t).getPassword()));
			isEmailValid(t);
			((Employee) t).setPassword(encryptPassword(t));
		}

		if (t instanceof Customer) {
			validateCustomerPhone(t);
		}

		T saved = repository.save(t);
		return saved;

	}

	public T update(T t, ID id) {

		if (t instanceof Customer) {
			Customer customer = ((CustomerRepo) repository).getById((Long) id);

			if (customer == null)
				throw DatabaseConstraintException.builder()
						.message(String.format("Customer does not exists in the database", (Long) id)).build();
		}

		if (t instanceof Employee) {
			isPasswordValid((((Employee) t).getPassword()));
		}

		repository.findById(id).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Update error: Couldn't find item with the id %d", id)).build());

		return repository.save(t);
	}

	public void deleteById(ID id) {
		repository.findById(id).orElseThrow(() -> ItemNotFoundException.builder()
				.message(String.format("Delete error: Couldn't find item with the id %d", id)).build());
		repository.deleteById(id);
	}
}
