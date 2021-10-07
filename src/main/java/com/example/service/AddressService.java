package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Address;
import com.example.entity.Customer;
import com.example.exception.ItemNotFoundException;
import com.example.repository.AddressRepo;
import com.example.repository.CustomerRepo;

@Service
@Transactional
public class AddressService extends AbstractService<Address, Long> {

	public AddressService(AddressRepo repository) {
		super(repository);
	}
}