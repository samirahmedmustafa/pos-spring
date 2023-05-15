package com.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Address;
import com.example.entity.Employee;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.entity.Status;
import com.example.entity.Supplier;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.AddressRepo;
import com.example.repository.EmployeeRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import com.example.repository.StatusRepo;
import com.example.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class EmployeeService extends PosService<Employee, Long> {
	
	private final EmployeeRepo repository;
	
	@Autowired
	private RoleRepo roleRepo;

	public EmployeeService(EmployeeRepo repository) {
		super(repository);
		this.repository = repository;
	}
	
	public void initRoleAndEmployee() {
		Role adminRole = new Role();
		adminRole.setName("Admin");
		
		Role userRole = new Role();
		userRole.setName("User");
		
		roleRepo.save(adminRole);
		roleRepo.save(userRole);
		
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		
		Employee adminEmployee = new Employee();
		adminEmployee.setAccountId("admin");
		adminEmployee.setPassword("admin123");
		adminEmployee.setEmail("admin123@email.com");
		adminEmployee.setRoles(roles);
		
		repository.save(adminEmployee);

		roles.remove(userRole);
		roles.add(adminRole);
		
		Employee userEmployee = new Employee();
		userEmployee.setAccountId("samirmustafa");
		userEmployee.setPassword("user123");
		userEmployee.setEmail("user123@email.com");
		userEmployee.setRoles(roles);
		
		repository.save(userEmployee);
		
	}

}
