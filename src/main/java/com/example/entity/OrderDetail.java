package com.example.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long unitPrice;
	private Long quantity;
	@Transient
	private Long subTotal;
	private Long discount;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Status status;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Order order;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Product product;

	public Long getSubTotal() {
		subTotal = quantity * unitPrice;
		return subTotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OrderDetail() {
		super();
	}

}
