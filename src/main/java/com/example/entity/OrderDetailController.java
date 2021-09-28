package com.example.entity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.ItemNotFoundException;
import com.example.repository.OrderDetailRepo;
import com.example.service.AbstractService;
import com.example.service.OrderDetailService;

@RestController
@RequestMapping("orderDetails")
public class OrderDetailController extends AbstractController<OrderDetail, Long> {

	public OrderDetailController(OrderDetailService service) {
		super(service);
	}

}
