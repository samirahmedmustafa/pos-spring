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
	@OneToMany(mappedBy = "paymentType")
	private List<Payment> payments;
	private Long name;

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

	public PaymentType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
