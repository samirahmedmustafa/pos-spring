package com.example.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment_types")
public class PaymentType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique=true)
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL)
	private List<InventoryPayment> inventoryPayments;
	@JsonIgnore
	@OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL)
	private List<OrderPayment> orderPayments;
	@JsonIgnore
	@OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL)
	private List<ExpensePayment> expensePayments;

	public Integer getId() {
		return id;
	}

	public List<InventoryPayment> getPayments() {
		return inventoryPayments;
	}

	public void setPayments(List<InventoryPayment> inventoryPayments) {
		this.inventoryPayments = inventoryPayments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
