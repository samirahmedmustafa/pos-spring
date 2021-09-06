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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer quantity;
	@Transient
	private Long subTotal;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Product product;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Status status;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Order order;

	public Long getSubTotal() {
		System.out.println("product: " + product);
		if(product != null)
			subTotal = quantity * product.getSellPrice();
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
