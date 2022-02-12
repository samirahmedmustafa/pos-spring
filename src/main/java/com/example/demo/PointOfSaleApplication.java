package com.example.demo;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.PosService;

@SpringBootApplication
@ComponentScan({ "com.example" })
@EntityScan("com.example.entity")
@EnableJpaRepositories({"com.example.repository"})
public class PointOfSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointOfSaleApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(PosService posService) {
		return args -> {
			Role roleUser = new Role(null, "ROLE_USER");
			Role roleADMIN = new Role(null, "ROLE_ADMIN");
			Role roleManager = new Role(null, "ROLE_MANAGER");
			Role roleSuperAdmin = new Role(null, "ROLE_SUPER_ADMIN");
			
			posService.saveRole(roleUser);
			posService.saveRole(roleADMIN);
			posService.saveRole(roleManager);
			posService.saveRole(roleSuperAdmin);
			
			User userSamir = new User(null, RandomStringUtils.randomAlphanumeric(10), "sahmed", "Samir",
					"Ahmed", "123", "samir.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null);
			User userYassin = new User(null, RandomStringUtils.randomAlphanumeric(10), "yahmed", "Yassin",
					"Ahmed", "123", "yassin.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null);
			User userRawan = new User(null, RandomStringUtils.randomAlphanumeric(10), "rahmed", "Rawan",
					"Ahmed", "123", "rawan.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null);
			User userMaryam = new User(null, RandomStringUtils.randomAlphanumeric(10), "mahmed", "Maryam",
					"Ahmed", "123", "maryam.ahmedmustafa@gmail.com", null, null, new Date(), true, true, null);
			
			posService.saveUser(userSamir);
			posService.saveUser(userYassin);
			posService.saveUser(userRawan);
			posService.saveUser(userMaryam);
			
			posService.addUserRole("sahmed", "ROLE_SUPER_ADMIN");
			posService.addUserRole("yahmed", "ROLE_MANAGER");
			posService.addUserRole("rahmed", "ROLE_ADMIN");
			posService.addUserRole("mahmed", "ROLE_USER");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
