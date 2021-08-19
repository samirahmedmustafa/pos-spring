package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Supplier;
import com.example.exception.ItemNotFoundException;
import com.example.repository.SupplierRepo;

@Service
@Transactional
public class SupplierService {

	private SupplierRepo supplierRepo;

	public SupplierService(SupplierRepo supplierRepo) {
		super();
		this.supplierRepo = supplierRepo;
	}

	public List<Supplier> getSuppliers() {
		return supplierRepo.findAll();
	}

	public Supplier getSupplierById(Integer id) {
		Supplier supplier = supplierRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find supplier with the id %d", id)));
		return supplier;
	}

	public Supplier save(Supplier supplier) {
		Supplier savedsupplier = supplierRepo.save(supplier);
		return savedsupplier;
	}

	public Supplier update(Supplier supplier, Integer id) {
		Supplier existingSupplier = supplierRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find supplier with the id %d", id)));
		Supplier updatedSupplier = updateSupplier(existingSupplier, supplier);
		return supplierRepo.save(updatedSupplier);
	}

	public void deleteById(Integer id) {
		supplierRepo.deleteById(id);
	}

	private Supplier updateSupplier(Supplier existingsupplier, Supplier supplier) {
		existingsupplier.setAddress(supplier.getAddress());
		existingsupplier.setCity(supplier.getCity());
		existingsupplier.setContact(supplier.getContact());
		existingsupplier.setCountry(supplier.getCountry());
		existingsupplier.setHomePage(supplier.getHomePage());
		existingsupplier.setName(supplier.getName());
		existingsupplier.setPhone(supplier.getPhone());
		existingsupplier.setPostalCode(supplier.getPostalCode());
		existingsupplier.setRegion(supplier.getRegion());
		existingsupplier.setTitle(supplier.getTitle());
		existingsupplier.setProducts(supplier.getProducts());
		return existingsupplier;
	}

}
