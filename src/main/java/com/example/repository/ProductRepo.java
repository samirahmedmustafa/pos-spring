package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	@Query("select p from Product p where p.barcode = ?1")
	Optional<Product> getByBarcode(String barcode);

	@Query("select p from Product p where p.name= ?1")
	Optional<Product> getByName(String name);
}
