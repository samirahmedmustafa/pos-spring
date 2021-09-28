package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderAddress;
import com.example.entity.OrderDetail;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CustomerRepo;
import com.example.repository.OrderAddressRepo;

@Service
@Transactional
public class OrderAddressService extends AbstractService<OrderAddress, Long> {

	public OrderAddressService(OrderAddressRepo repository) {
		super(repository);
	}
}
