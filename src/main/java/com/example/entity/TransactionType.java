package com.example.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_types")
public class TransactionType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Long name;
	@OneToMany(mappedBy = "paymentType")
	private List<Payment> payments;
	@OneToMany(mappedBy = "transactionType")
	private List<TransactionType> transactionType;

	public List<TransactionType> getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(List<TransactionType> transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getId() {
		return id;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
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

	public TransactionType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
