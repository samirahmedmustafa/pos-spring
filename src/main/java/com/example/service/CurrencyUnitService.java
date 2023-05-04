package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Address;
import com.example.entity.Category;
import com.example.entity.CurrencyUnit;
import com.example.entity.Customer;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CategoryRepo;
import com.example.repository.CurrencyUnitRepo;
import com.example.repository.CustomerRepo;

@Service
@Transactional
public class CurrencyUnitService extends AbstractService<CurrencyUnit, Long> {

	public CurrencyUnitService(CurrencyUnitRepo repository) {
		super(repository);
	}
}
