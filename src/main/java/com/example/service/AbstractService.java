package com.example.service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.City;
import com.example.entity.InventoryDetail;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CityRepo;
import com.example.repository.InventoryDetailRepo;
import com.example.repository.OrderDetailRepo;
import com.example.repository.ProductRepo;

public class AbstractService<T, ID> {

	private JpaRepository<T, ID> repository;

	public AbstractService(JpaRepository<T, ID> repository) {
		super();
		this.repository = repository;
	}

	public List<T> getAll() {
		return repository.findAll();
	}

	public T getById(ID id) {
		T t = repository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", id)));
		return t;
	}

	public List<OrderDetail> getByOrder(Long orderNo) {
		List<OrderDetail> orderDetails = ((OrderDetailRepo) repository).getByOrder(orderNo).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", orderNo)));
		return orderDetails;
	}

	public List<InventoryDetail> getByInventory(Long inventory) {
		List<InventoryDetail> inventoryDetails = ((InventoryDetailRepo) repository).getByInventory(inventory)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Invalid inventory %d", inventory)));
		return inventoryDetails;
	}

	public Product getByBarcode(String barcode) {
		Product product = ((ProductRepo) repository).getByBarcode(barcode).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find item with the barcode %s", barcode)));
		return product;
	}

	public List<City> getCitiesByCountry(String country) {
		List<City> cities = ((CityRepo) repository).findCitiesByCountry(country).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", country)));
		return cities;
	}

	public T save(T t) {
		T saved = repository.save(t);
		return saved;
	}

	public T update(T t, ID id) {
		repository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find item with the id %d", id)));
		return repository.save(t);
	}

	public void deleteById(ID id) {
		repository.deleteById(id);
	}
}
