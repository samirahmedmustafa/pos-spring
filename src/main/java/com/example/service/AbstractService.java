package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.entity.City;
import com.example.entity.Country;
import com.example.entity.Customer;
import com.example.entity.Employee;
import com.example.entity.Expense;
import com.example.entity.InventoryDetail;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;
import com.example.repository.CountryRepo;
import com.example.repository.CustomerRepo;
import com.example.repository.EmployeeRepo;
import com.example.repository.InventoryDetailRepo;
import com.example.repository.OrderDetailRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;

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
		return ((EmployeeRepo) repository).findByAccountId(accountId).orElseThrow(() -> DatabaseConstraintException.builder().message(String.format("Account id %s not found", accountId)).build());
	}

	public Role getByRoleName(String name) {
		return ((RoleRepo) repository).findByName(name);//.orElseThrow(() -> DatabaseConstraintException.builder()
//				.message(String.format("Invalid role name %s", name)).build());
	}

	public Employee login(Employee employee) {

		Employee existingEmployee = ((EmployeeRepo) repository).findByAccountId(employee.getAccountId()).orElseThrow(() -> DatabaseConstraintException.builder().message(String.format("Invalid accountId or password")).build());

		if (!BCrypt.checkpw(employee.getPassword(), existingEmployee.getPassword()))
			throw DatabaseConstraintException.builder().message(String.format("Invalid accountId or password")).build();

		return existingEmployee;
	}

	public Employee getByEmail(String email) {
		return ((EmployeeRepo) repository).findByEmail(email);
//				.orElseThrow(() -> DatabaseConstraintException.builder()
//				.message(String.format("Email %s not found", email)).build());
	}

	public Product getProductByName(String name) {
		return ((ProductRepo) repository).findByName(name);
//				.orElseThrow(() -> DatabaseConstraintException.builder()
//				.message(String.format("Invalid Product name %s", name)).build());
	}

	public Customer getByPhone(String phone) {
		return ((CustomerRepo) repository).findByPhone(phone);
//		.orElseThrow(() -> DatabaseConstraintException.builder()
//				.message(String.format("No record with the phone %s", phone)).build());
	}

	public List<OrderDetail> getByOrder(Long orderNo) {

		List<OrderDetail> orderDetails = ((OrderDetailRepo) repository).findByOrder(orderNo).get();
		return orderDetails;
	}

	public List<InventoryDetail> getByInventory(Long inventory) {
		List<InventoryDetail> inventoryDetails = ((InventoryDetailRepo) repository).findByInventory(inventory);
//				.orElseThrow(() -> DatabaseConstraintException.builder()
//						.message(String.format("No inventory details with the inventory id %s", inventory)).build());
		return inventoryDetails;
	}

	public Product getByBarcode(String barcode) {
		Product product = ((ProductRepo) repository).findByBarcode(barcode);
//		.orElseThrow(() -> DatabaseConstraintException
//				.builder().message(String.format("Couldn't find item with the barcode %s", barcode)).build());
		return product;
	}

//	public Boolean isCountryNameExists(String name) {
//		Boolean isExist = ((CountryRepo) repository).findByName(name);
//
//		if (isExist)
//			throw DatabaseConstraintException.builder().message(String.format("Country is already exists %s", name))
//					.build();
//
//		return false;
//	}

	public Product debitProduct(Long id, Integer currentStock) {
		Product product = ((ProductRepo) repository).getById(id);
		
		product.setCurrentStock(product.getCurrentStock() + currentStock);
		product = ((ProductRepo) repository).save(product);
		return product;
	}

	public List<City> getCitiesByCountry(String country) {
		List<City> cities = ((CityRepo) repository).findByCountry(country);
//				.orElseThrow(() -> DatabaseConstraintException.builder()
//						.message(String.format("Couldn't find cities in the country %s", country)).build());
		return cities;
	}

	public Country getCountryByCode(String code) {
		Country country = ((CountryRepo) repository).findByCode(code);
		return country;
	}

	private Boolean isCountryCodeExist(String code) {
		Country country = ((CountryRepo) repository).findByCode(code);

		if (country != null)
			throw DatabaseConstraintException.builder()
					.message(String.format("Country code %s already exists in the database", (code))).build();

		return true;
	}

	private Boolean isCountryNameExist(String name) {
		Country country = ((CountryRepo) repository).findByName(name);
		
		if (country == null)
			throw DatabaseConstraintException.builder()
					.message(String.format("Country name %s already exists in the database", (name))).build();

		return true;
	}

	private Boolean isEmployeeAccountIdExist(String accountId) {

		Employee employee = ((EmployeeRepo) repository).findByAccountId(accountId).orElseThrow(() -> DatabaseConstraintException.builder().message(String.format("Invalid accountId or password")).build());

		if (employee != null)
			throw DatabaseConstraintException.builder()
					.message(String.format("Employee account Id %s already exists in the database", (accountId)))
					.build();

		return true;
	}

	private Boolean isEmailUsed(String email) {

		Employee employee = ((EmployeeRepo) repository).findByEmail(email);

		if (employee != null)
			throw DatabaseConstraintException.builder()
					.message(String.format("Employee email %s already exists in the database", email)).build();

		return false;
	}

	private Boolean isEmailEmpty(String email) {

		Boolean emailIsEmpty = (email == null || email.replaceAll("\\s+", "").length() == 0);

		if (emailIsEmpty)
			throw DatabaseConstraintException.builder().message(String.format("Employee email is invalid", email))
					.build();

		return false;
	}

	private String encryptPassword(String password) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
		return hashedPassword;
	}

	public void validateCustomerPhone(T t) {
		Customer customer = ((CustomerRepo) repository).findByPhone(((Customer) t).getPhone());

//		if (isExist)
//			throw DatabaseConstraintException.builder()
//					.message(String.format("Phone no. %s already exists in the database", ((Customer) t).getPhone()))
//					.build();
	}

	public void isPasswordNotEmpty(String password) {

		if (password == null || password.replaceAll("\\s+", "").length() == 0)
			throw DatabaseConstraintException.builder().message(String.format("Employee password cannot be null"))
					.build();
	}

	public T save(T t) {

		if (t instanceof Country) {
			isCountryCodeExist(((Country) t).getCode());
			isCountryNameExist(((Country) t).getName());
		}

		if (t instanceof Employee) {
			isEmployeeAccountIdExist((((Employee) t).getAccountId()));
			isPasswordNotEmpty((((Employee) t).getPassword()));
			isEmailEmpty((((Employee) t).getEmail()));
			isEmailUsed((((Employee) t).getEmail()));

			((Employee) t).setPassword(encryptPassword(((Employee) t).getPassword()));
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
			isPasswordNotEmpty((((Employee) t).getPassword()));
			isEmailEmpty((((Employee) t).getEmail()));
			((Employee) t).setPassword(encryptPassword(((Employee) t).getPassword()));
		}

		repository.findById(id).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Update error: Couldn't find item with the id %d", id)).build());

		return repository.save(t);
	}

	public void deleteById(ID id) {
		repository.findById(id).orElseThrow(() -> DatabaseConstraintException.builder()
				.message(String.format("Delete error: Couldn't find item with the id %d", id)).build());
		repository.deleteById(id);
	}
}
