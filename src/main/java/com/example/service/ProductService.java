package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Product;
import com.example.exception.ItemNotFoundException;
import com.example.repository.ProductRepo;

@Service
@Transactional
public class ProductService {

	private ProductRepo productRepo;

	public ProductService(ProductRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}

	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(Long id) {
		Product product = productRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find product with the id %d", id)));
		return product;
	}

	public Product save(Product product) {
		Product savedProduct = productRepo.save(product);
		return savedProduct;
	}

	public Product update(Product product, Long id) {
		Product existingProduct = productRepo.findById(id).orElseThrow(
				() -> new ItemNotFoundException(String.format("Couldn't find product with the id %d", id)));
		Product updatedProduct = updateProduct(existingProduct, product);
		return productRepo.save(updatedProduct);
	}

	public void deleteById(Long id) {
		productRepo.deleteById(id);
	}

	private Product updateProduct(Product existingProduct, Product product) {
		existingProduct.setImg(product.getImg());
		existingProduct.setName(product.getName());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setNameAr(product.getNameAr());
		existingProduct.setInventory(product.getInventory());
		existingProduct.setListPrice(product.getListPrice());
		existingProduct.setOrderDetail(product.getOrderDetail());
		existingProduct.setQuantityPerUnit(product.getQuantityPerUnit());
		existingProduct.setSupplier(product.getSupplier());
		existingProduct.setBarCode(product.getBarCode());
		return existingProduct;
	}

}
