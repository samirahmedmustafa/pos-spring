package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Expense expense;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private CurrencyUnit currencyUnit;

}
