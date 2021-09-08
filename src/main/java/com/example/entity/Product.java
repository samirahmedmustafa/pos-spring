package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "products")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String nameAr;
	private String img;
	private String barCode;
	private String quantityPerUnit;
	private Float vatPercentage;
	@Transient
	private Float vatValue;
	private Long sellPrice;
	private Integer quantity;
	private Long discount;
	private Double averageCost;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Category category;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Supplier supplier;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<InventoryDetail> inventoryDetails;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setVatValue(Float vatValue) {
		this.vatValue = vatValue;
	}

	public void setAverageCost(Double averageCost) {
		this.averageCost = averageCost;
	}

	public Integer getQuantity() {
//		quantity = 0;
//		System.out.println("inventories: " + inventoryDetails);
//		System.out.println("orders: " + orderDetails);
//		this.inventoryDetails.stream().forEach((inventories)->{
//			quantity += inventories.getQuantity();
//		});
//		this.orderDetails.stream().forEach((orders)->{
//			quantity -= orders.getQuantity();
//		});
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public Float getVatPercentage() {
		return vatPercentage;
	}

	public void setVatPercentage(Float vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

	public Float getVatValue() {
		if (vatPercentage != null)
			return (sellPrice * vatPercentage) / 100;
		return vatValue;
	}

	public Double getAverageCost() {
		return averageCost;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Long getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Long sellPrice) {
		this.sellPrice = sellPrice;
	}

	public List<InventoryDetail> getInventoryDetails() {
		return inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetail> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public String getNameAr() {
		return nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Product() {
		super();
	}

}
