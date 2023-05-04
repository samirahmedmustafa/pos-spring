package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CategoryRepo;
import com.example.service.AbstractService;
import com.example.service.CategoryService;
import com.example.service.CurrencyUnitService;

@RestController
@RequestMapping("currencyUnits")
public class CurrencyUnitController extends AbstractController<CurrencyUnit, Long> {

	public CurrencyUnitController(CurrencyUnitService service) {
		super(service);
	}
}
