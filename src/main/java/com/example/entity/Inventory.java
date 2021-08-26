package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "inventories")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Transient
	private Long totalAmount;
	@Temporal(TemporalType.DATE)
	private Date inventoryDate;
	@OneToMany(mappedBy = "inventory")
	private List<InventoryDetail> inventoryDetails;
	@OneToMany(mappedBy = "inventory")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inventory() {
		super();
	}

}
