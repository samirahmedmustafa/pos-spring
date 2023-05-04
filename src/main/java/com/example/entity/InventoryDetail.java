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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer quantity;
	private Long unitPrice;
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	@Temporal(TemporalType.DATE)
	private Date manufacturingDate;
	@Transient
	private Long subTotal;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Product product;
//	@JsonBackReference("inventory-inventorydetails")
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Inventory inventory;
	
	public Long getSubTotal() {
		subTotal = quantity * unitPrice;
		return subTotal;
	}

}
