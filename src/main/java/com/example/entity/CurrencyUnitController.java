package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CategoryRepo;
import com.example.service.AddressService;
import com.example.service.CategoryService;
import com.example.service.CityService;
import com.example.service.CurrencyUnitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = { "/", "/api/currencyUnits" })
public class CurrencyUnitController extends PosController<CurrencyUnit, Long> {

	private final CurrencyUnitService service;

	public CurrencyUnitController(CurrencyUnitService service) {
		super(service);
		this.service = service;
	}
}
