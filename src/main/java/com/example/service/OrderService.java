package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Order;
import com.example.exception.ItemNotFoundException;
import com.example.repository.OrderRepo;

@Service
@Transactional
public class OrderService {

	private OrderRepo orderRepo;

	public OrderService(OrderRepo orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	public List<Order> getOrders() {
		return orderRepo.findAll();
	}

	public Order getOrderById(Long id) {
		Order order = orderRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Order with the id %d", id)));
		return order;
	}

	public Order save(Order order) {
		Order savedOrder = orderRepo.save(order);
		return savedOrder;
	}

	public Order update(Order order, Long id) {
		Order existingOrder = orderRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Order with the id %d", id)));
		Order updatedOrder = updateOrder(existingOrder, order);
		return orderRepo.save(updatedOrder);
	}

	public void deleteById(Long id) {
		orderRepo.deleteById(id);
	}

	private Order updateOrder(Order existingOrder, Order order) {
		existingOrder.setCustomer(order.getCustomer());
		existingOrder.setEmployee(order.getEmployee());
		existingOrder.setOrderDate(order.getOrderDate());
		existingOrder.setPayment(order.getPayment());
		existingOrder.setProducts(order.getProducts());
		existingOrder.setShipAddress(order.getShipAddress());
		existingOrder.setShipCity(order.getShipCity());
		existingOrder.setShipCountry(order.getShipCountry());
		existingOrder.setShipName(order.getShipName());
		existingOrder.setShippedDate(order.getShippedDate());
		existingOrder.setShipper(order.getShipper());
		existingOrder.setShipPostalCode(order.getShipPostalCode());
		existingOrder.setShipRegion(order.getShipRegion());
		return existingOrder;
	}

}
