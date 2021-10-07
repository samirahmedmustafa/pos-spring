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

	private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	private JpaRepository<T, ID> repository;

	public AbstractService(JpaRepository<T, ID> repository) {
		super();
		this.repository = repository;
	}

	public List<T> getAll() {
		return repository.findAll();
	}

	public T getById(ID id) {

		T t = repository.findById(id).orElseThrow(() -> ItemNotFoundException.builder()
				.message(String.format("Couldn't find item with the id %s", id)).build());
		return t;
	}

	public Employee getByAccountId(String accountId) {
		return ((EmployeeRepo) repository).getByAccountId(accountId).orElseThrow(() -> ItemNotFoundException.builder()
				.message(String.format("Account id %s not found", accountId)).build());
	}

	public List<OrderDetail> getByOrder(Long orderNo) {

		List<OrderDetail> orderDetails = ((OrderDetailRepo) repository).getByOrder(orderNo).get();
		return orderDetails;
	}

	public List<InventoryDetail> getByInventory(Long inventory) {
		List<InventoryDetail> inventoryDetails = ((InventoryDetailRepo) repository).getByInventory(inventory)
				.orElseThrow(() -> ItemNotFoundException.builder()
						.message(String.format("No inventory details with the inventory id %s", inventory)).build());
		return inventoryDetails;
	}

	public Product getByBarcode(String barcode) {
		Product product = ((ProductRepo) repository).getByBarcode(barcode).orElseThrow(() -> ItemNotFoundException
				.builder().message(String.format("Couldn't find item with the barcode %s", barcode)).build());
		return product;
	}

	public Product debitProduct(Long id, Integer quantity) {
		Product product = ((ProductRepo) repository).getById(id);
		product.setQuantity(product.getQuantity() + quantity);
		product = ((ProductRepo) repository).save(product);
		return product;
	}

	public List<City> getCitiesByCountry(String country) {
		List<City> cities = ((CityRepo) repository).findCitiesByCountry(country).orElseThrow(() -> ItemNotFoundException
				.builder().message(String.format("Couldn't find cities in the country %s", country)).build());
		return cities;
	}

	public Country getCountryByCode(String code) {
		Country country = ((CountryRepo) repository).getCountryByCode(code).get();
		return country;
	}

	public T save(T t) {

		if (t instanceof Country) {
			Boolean isCodeExist = ((CountryRepo) repository).getCountryByCode(((Country) t).getCode()).isPresent();
			
			if (isCodeExist)
				throw DatabaseConstraintException.builder().message(
						String.format("Country code %s already exists in the database", ((Country) t).getCode()))
						.build();
			
			Boolean isNameExist = ((CountryRepo) repository).getCountryByName(((Country) t).getName()).isPresent();
			if(isNameExist)
				throw DatabaseConstraintException.builder().message(
						String.format("Country name %s already exists in the database", ((Country) t).getName()))
						.build();
		} else if (t instanceof Employee) {
			if (((EmployeeRepo)repository).getByAccountId(((Employee) t).getAccountId()) != null)
				throw DatabaseConstraintException.builder().message(
						String.format("Employee account Id %s already exists in the database", ((Employee) t).getAccountId()))
						.build();
			else if(((EmployeeRepo)repository).getByEmail(((Employee) t).getEmail()) != null)
				throw DatabaseConstraintException.builder().message(
						String.format("Employee account Id %s already exists in the database", ((Employee) t).getEmail()))
						.build();
		} else if (t instanceof Customer) {
			
			Boolean isExist = ((CustomerRepo)repository).getByPhone(((Customer)t).getPhone()).isPresent();
			
			if (isExist)
				throw DatabaseConstraintException.builder().message(
						String.format("Phone no. %s already exists in the database", ((Customer) t).getPhone()))
						.build();
		}

		T saved = repository.save(t);
		return saved;

	}

	public T update(T t, ID id) {
		repository.findById(id).orElseThrow(() -> ItemNotFoundException.builder()
				.message(String.format("Update error: Couldn't find item with the id %d", id)).build());
		try {
			return repository.save(t);
		} catch (DataIntegrityViolationException e) {
			throw DatabaseConstraintException.builder().message(e.getMessage()).build();
		}
	}

	public void deleteById(ID id) {
		repository.findById(id).orElseThrow(() -> ItemNotFoundException.builder()
				.message(String.format("Delete error: Couldn't find item with the id %d", id)).build());
		repository.deleteById(id);
	}
}
