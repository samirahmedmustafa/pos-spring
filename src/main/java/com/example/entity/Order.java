package com.example.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date shippedDate;
	@Transient
	private Long totalAmount;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonBackReference("orderDetails-order")
	private List<OrderDetail> orderDetails;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	@JsonManagedReference(value = "shipper-order")
	private Shipper shipper;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	@JsonManagedReference(value = "order-customer")
	private Customer customer;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	@JsonManagedReference(value = "employee-order")
	private Employee employee;

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Order() {
		super();
	}

	public Long getTotalAmount() {
		
		totalAmount = (long) 0;
		
		this.getOrderDetails().stream().forEach(elem -> {
			totalAmount += elem.getSubTotal();
		});
		
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

}
