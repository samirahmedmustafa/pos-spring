package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("logins")
public class LoginController extends AbstractController<Employee, Integer> {
	
	public LoginController(EmployeeService service) {
		super(service);
	}
}
