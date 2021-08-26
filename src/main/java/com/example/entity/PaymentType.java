package com.example.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "payment_types")
public class PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Long name;
	@OneToMany(mappedBy = "paymentType")
	private List<InventoryPayment> inventoryPayments;
	@OneToMany(mappedBy = "paymentType")
	private List<OrderPayment> orderPayments;
	@OneToMany(mappedBy = "paymentType")
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

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
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