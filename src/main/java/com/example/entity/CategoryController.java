package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CategoryRepo;
import com.example.service.AbstractService;
import com.example.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController extends AbstractController<Category, Integer> {

	public CategoryController(CategoryService service) {
		super(service);
	}
}
