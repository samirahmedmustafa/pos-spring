package com.example.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "inventory_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryPayment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	private Long amount;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private PaymentType paymentType;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Inventory inventory;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private CurrencyUnit currencyUnit;
}
