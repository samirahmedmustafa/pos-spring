package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer quantity;
	@Transient
	private BigDecimal subTotal;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Product product;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Order order;

	public BigDecimal getSubTotal() {
//		System.out.println("product: " + product);
//		System.out.println("product.name: " + product.getName());
//		System.out.println("product.price: " + product.getSellPrice());
		if(product != null)
			subTotal = product.getSellPrice().multiply(BigDecimal.valueOf(quantity));
		return subTotal;
	}

}
