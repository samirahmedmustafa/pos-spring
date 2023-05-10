package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	private BigDecimal totalAmount;
	@Transient
	private String receiptNo;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Shipper shipper;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Employee employee;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Status status;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderPayment> orderPayments;
	@ManyToMany
	@JoinTable(name = "orders_addresses", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<Address> addresses;

	public BigDecimal getTotalAmount() {
		totalAmount = BigDecimal.ZERO;
		if (!this.orderDetails.isEmpty())
			this.orderDetails.stream().forEach(orderDetail -> totalAmount = totalAmount.add(
					orderDetail.getProduct().getSellPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity()))));
		return totalAmount;
	}

}
