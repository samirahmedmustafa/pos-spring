package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
