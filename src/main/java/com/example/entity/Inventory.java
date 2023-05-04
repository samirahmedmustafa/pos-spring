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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date inventoryDate;
	@Transient
	private Long totalAmount;
//	@JsonManagedReference("inventory-inventorydetails")
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<InventoryDetail> inventoryDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<InventoryPayment> inventoryPayments;
	
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

}
