package com.example.demo;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.security.SecurityConfiguration;
import com.example.service.EmployeeService;
import com.example.service.PosService;
import com.example.service.RoleService;

@SpringBootApplication
@ComponentScan({ "com.example" })
@EntityScan("com.example.entity")
@EnableJpaRepositories({"com.example.repository"})
public class PointOfSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointOfSaleApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(EmployeeService employeeService, RoleService roleService) {
		return args -> {
//			Role roleUser = new Role(null, "ROLE_USER");
//			Role roleADMIN = new Role(null, "ROLE_ADMIN");
//			Role roleManager = new Role(null, "ROLE_MANAGER");
//			Role roleSuperAdmin = new Role(null, "ROLE_SUPER_ADMIN");
//			
//			roleService.save(roleUser);
//			roleService.save(roleADMIN);
//			roleService.save(roleManager);
//			roleService.save(roleSuperAdmin);
//			
//			User userSamir = new User(null, RandomStringUtils.randomAlphanumeric(10), "sahmed", "Samir",
//					"Ahmed", "123", "samir.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null,
//					null, null, null, null, null);
//			User userYassin = new User(null, RandomStringUtils.randomAlphanumeric(10), "yahmed", "Yassin",
//					"Ahmed", "123", "yassin.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null,
//					null, null, null, null, null);
//			User userRawan = new User(null, RandomStringUtils.randomAlphanumeric(10), "rahmed", "Rawan",
//					"Ahmed", "123", "rawan.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null,
//					null, null, null, null, null);
//			User userMaryam = new User(null, RandomStringUtils.randomAlphanumeric(10), "mahmed", "Maryam",
//					"Ahmed", "123", "maryam.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null,
//					null, null, null, null, null);
//			
//			userService.save(userSamir);
//			userService.save(userYassin);
//			userService.save(userRawan);
//			userService.save(userMaryam);
//			
//			userService.addUserRole("sahmed", "ROLE_SUPER_ADMIN");
//			userService.addUserRole("yahmed", "ROLE_MANAGER");
//			userService.addUserRole("rahmed", "ROLE_ADMIN");
//			userService.addUserRole("mahmed", "ROLE_USER");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
