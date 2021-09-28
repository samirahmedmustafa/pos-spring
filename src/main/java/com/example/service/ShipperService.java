package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Shipper;
import com.example.entity.Status;
import com.example.repository.ShipperRepo;

@Service
@Transactional
public class ShipperService extends AbstractService<Shipper, Integer> {

	public ShipperService(ShipperRepo repository) {
		super(repository);
	}
}
