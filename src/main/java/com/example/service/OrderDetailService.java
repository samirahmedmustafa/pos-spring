package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.OrderDetailRepo;

@Service
@Transactional
public class OrderDetailService extends AbstractService<OrderDetail, Long> {

	public OrderDetailService(OrderDetailRepo repository) {
		super(repository);
	}
}
