package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
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
