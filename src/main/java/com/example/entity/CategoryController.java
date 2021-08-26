package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CategoryRepo;

@RestController
@RequestMapping("categories")
public class CategoryController extends AbstractController<CategoryRepo, Category, Integer> {

	public CategoryController(CategoryRepo r) {
		super(r);
	}
}
