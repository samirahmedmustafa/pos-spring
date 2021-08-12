package com.example.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        @GetMapping
        public ResponseEntity<List<Category>> getCategories() {
                List<Category> categories = categoryService.getCategories();
                return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
        }

        @GetMapping("{id}")
        public ResponseEntity<Category> getCategory(@PathVariable Integer id) {
                Category category = categoryService.getCategoryById(id);
                return new ResponseEntity<Category>(category, HttpStatus.OK);
        }

        @PostMapping("")
        public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
                Category savedCategory = categoryService.save(category);
                return new ResponseEntity<Category>(savedCategory, HttpStatus.CREATED);
        }

        @PutMapping("{id}")
        public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Integer id) {
                Category updatedCategory = categoryService.update(category, id);
                return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> updateCategory(@PathVariable Integer id) {
                categoryService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
        }

}
