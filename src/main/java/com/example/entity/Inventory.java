package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "inventories")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private Long totalAmount;
	@Temporal(TemporalType.DATE)
	private Date inventoryDate;
	@JsonManagedReference("inventory-inventorydetails")
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<InventoryDetail> inventoryDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<InventoryPayment> inventoryPayments;
	
	public List<InventoryPayment> getInventoryPayments() {
		return inventoryPayments;
	}

	public void setInventoryPayments(List<InventoryPayment> inventoryPayments) {
		this.inventoryPayments = inventoryPayments;
	}

	public List<InventoryDetail> getInventoryDetails() {
		return inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetail> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	public Long getTotalAmount() {
		
		totalAmount = 0L;
		
		if(inventoryDetails != null) {
			inventoryDetails.stream().forEach(inventoryDetails -> {
				if(inventoryDetails.getUnitPrice() != null && inventoryDetails.getQuantity() != null)
					totalAmount += (inventoryDetails.getUnitPrice() * inventoryDetails.getQuantity());
			});
		}
		
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<InventoryDetail> getInventoryTransactions() {
		return inventoryDetails;
	}

	public void setInventoryTransactions(List<InventoryDetail> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inventory() {
		super();
	}

	public Inventory(Long id, Long totalAmount, Date inventoryDate, List<InventoryDetail> inventoryDetails,
			List<InventoryPayment> inventoryPayments) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.inventoryDate = inventoryDate;
		this.inventoryDetails = inventoryDetails;
		this.inventoryPayments = inventoryPayments;
	}

}
