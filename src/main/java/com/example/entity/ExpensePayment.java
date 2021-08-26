package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "expense_payments")
public class ExpensePayment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long amount;
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private PaymentType paymentType;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Expense expense;

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Long getId() {
		return id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public ExpensePayment() {
		super();
		// TODO Auto-generated constructor stub
	}

}