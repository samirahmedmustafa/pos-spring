package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.OrderDetail;
import com.example.exception.ItemNotFoundException;
import com.example.repository.OrderDetailRepo;

@Service
@Transactional
public class OrderDetailService {

	private OrderDetailRepo orderDetailRepo;

	public OrderDetailService(OrderDetailRepo orderDetailRepo) {
		super();
		this.orderDetailRepo = orderDetailRepo;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetailRepo.findAll();
	}

	public OrderDetail getOrderDetailById(Long id) {
		OrderDetail orderDetail = orderDetailRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Order Detail with the id %d", id)));
		return orderDetail;
	}

	public OrderDetail save(OrderDetail orderDetail) {
		OrderDetail savedOrderDetail = orderDetailRepo.save(orderDetail);
		return savedOrderDetail;
	}

	public OrderDetail update(OrderDetail orderDetail, Long id) {
		OrderDetail existingOrderDetail = orderDetailRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Order Detail with the id %d", id)));
		OrderDetail updatedOrderDetail = updateOrderDetail(existingOrderDetail, orderDetail);
		return orderDetailRepo.save(updatedOrderDetail);
	}

	public void deleteById(Long id) {
		orderDetailRepo.deleteById(id);
	}

	private OrderDetail updateOrderDetail(OrderDetail existingOrderDetail, OrderDetail orderDetail) {
		existingOrderDetail.setDiscount(orderDetail.getDiscount());
		existingOrderDetail.setOrder(orderDetail.getOrder());
		existingOrderDetail.setProduct(orderDetail.getProduct());
		existingOrderDetail.setQuantity(orderDetail.getQuantity());
		existingOrderDetail.setStatus(orderDetail.getStatus());
		existingOrderDetail.setUnitPrice(orderDetail.getUnitPrice());
		return existingOrderDetail;
	}

}
