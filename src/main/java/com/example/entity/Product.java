package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	@NotNull
	private String name;
	@Column(unique=true)
	private String nameAr;
	private String img;
	@Column(unique=true)
	private String barcode;
	private String quantityPerUnit;
	private BigDecimal vatPercentage;
	@Transient
	private BigDecimal vatValue;
	private BigDecimal sellPrice;
	private BigDecimal depreciation;
	private Integer currentStock;
	private BigDecimal discount;
	private BigDecimal averageCost;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Category category;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Supplier supplier;
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<InventoryDetail> inventoryDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

	public Integer getCurrentStock() {
//		quantity = 0;
//		System.out.println("inventories: " + inventoryDetails);
//		System.out.println("orders: " + orderDetails);
//		this.inventoryDetails.stream().forEach((inventories)->{
//			quantity += inventories.getQuantity();
//		});
//		this.orderDetails.stream().forEach((orders)->{
//			quantity -= orders.getQuantity();
//		});
		return currentStock;
	}

	public BigDecimal getVatValue() {
		if (vatPercentage != null)
			return sellPrice.multiply(vatPercentage).divide(BigDecimal.valueOf(100));
		return vatValue;
	}
}
