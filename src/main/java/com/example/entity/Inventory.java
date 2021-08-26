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
@Table(name = "inventory_transactions")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Transient
	private Long totalAmount;
	@OneToMany(mappedBy = "inventory")
	private List<InventoryTransaction> inventoryTransactions;
	@OneToMany(mappedBy = "inventory")
	private List<InventoryPayment> inventoryPayments;
	@Temporal(TemporalType.DATE)
	private Date inventoryTransDate;
	
	public List<InventoryPayment> getInventoryPayments() {
		return inventoryPayments;
	}

	public void setInventoryPayments(List<InventoryPayment> inventoryPayments) {
		this.inventoryPayments = inventoryPayments;
	}

	public Date getInventoryTransDate() {
		return inventoryTransDate;
	}

	public void setInventoryTransDate(Date inventoryTransDate) {
		this.inventoryTransDate = inventoryTransDate;
	}

	public Long getTotalAmount() {
		
		totalAmount = 0L;
		
		if(inventoryTransactions != null) {
			inventoryTransactions.stream().forEach(inventoryTrans -> {
				if(inventoryTrans.getUnitPrice() != null && inventoryTrans.getQuantity() != null)
					totalAmount += (inventoryTrans.getUnitPrice() * inventoryTrans.getQuantity());
			});
		}
		
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<InventoryTransaction> getInventoryTransactions() {
		return inventoryTransactions;
	}

	public void setInventoryTransactions(List<InventoryTransaction> inventoryTransactions) {
		this.inventoryTransactions = inventoryTransactions;
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
