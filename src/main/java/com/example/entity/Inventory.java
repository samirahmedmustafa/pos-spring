package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Product product;
	private Integer quantity;
	private Long purchasePrice;
	private Long sellPrice;
	
	public Long getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Long getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Long sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inventory() {
		super();
	}

}
