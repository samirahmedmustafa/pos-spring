package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Shipper;
import com.example.exception.ItemNotFoundException;
import com.example.repository.ShipperRepo;

@Service
@Transactional
public class ShipperService {

	private ShipperRepo shipperRepo;

	public ShipperService(ShipperRepo shipperRepo) {
		super();
		this.shipperRepo = shipperRepo;
	}

	public List<Shipper> getShippers() {
		return shipperRepo.findAll();
	}

	public Shipper getShipperById(Integer id) {
		Shipper shipper = shipperRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Shipper with the id %d", id)));
		return shipper;
	}

	public Shipper save(Shipper shipper) {
		Shipper savedShipper = shipperRepo.save(shipper);
		return savedShipper;
	}

	public Shipper update(Shipper shipper, Integer id) {
		Shipper existingShipper = shipperRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Shipper with the id %d", id)));
		Shipper updatedShipper = updateShipper(existingShipper, shipper);
		return shipperRepo.save(updatedShipper);
	}

	public void deleteById(Integer id) {
		shipperRepo.deleteById(id);
	}

	private Shipper updateShipper(Shipper existingShipper, Shipper shipper) {
		existingShipper.setName(shipper.getName());
		existingShipper.setPhone(shipper.getPhone());
		existingShipper.setOrders(shipper.getOrders());
		return existingShipper;
	}

}
