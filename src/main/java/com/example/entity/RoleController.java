package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.AbstractService;
import com.example.service.AddressService;
import com.example.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController extends AbstractController<Role, Long> {
	
	public RoleController(RoleService service) {
		super(service);
	}
}
