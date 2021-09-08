package com.example.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "expense_types")
public class ExpenseType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String expenseName;
	@JsonBackReference("expenseType-expense")
	@OneToMany(mappedBy = "expenseType")
	private List<Expense> expenses;

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public ExpenseType() {
		super();
	}

}
