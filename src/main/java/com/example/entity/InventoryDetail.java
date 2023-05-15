package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
import jakarta.persistence.Transient;
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
