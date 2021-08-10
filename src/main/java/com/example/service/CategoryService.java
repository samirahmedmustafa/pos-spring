package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Category;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CategoryRepo;

@Service
@Transactional
public class CategoryService {

	private CategoryRepo categoryRepo;

	public CategoryService(CategoryRepo categoryRepo) {
		super();
		this.categoryRepo = categoryRepo;
	}

	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	public Category getCategoryById(Integer id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Category with the id %d", id)));
		return category;
	}

	public Category save(Category category) {
		Category savedCategory = categoryRepo.save(category);
		return savedCategory;
	}

	public Category update(Category category, Integer id) {
		Category existingCategory = categoryRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Category with the id %d", id)));
		Category updatedCategory = updateCategory(existingCategory, category);
		return categoryRepo.save(updatedCategory);
	}

	public void deleteById(Integer id) {
		categoryRepo.deleteById(id);
	}

	private Category updateCategory(Category existingCategory, Category category) {
		existingCategory.setDescription(category.getDescription());
		existingCategory.setName(category.getName());
		existingCategory.setProducts(category.getProducts());
		return existingCategory;
	}

}
