package com.example.entity;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
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
}
