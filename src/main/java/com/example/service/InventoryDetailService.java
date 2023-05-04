package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import com.example.entity.Inventory;
import com.example.entity.InventoryDetail;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.entity.Supplier;
import com.example.entity.User;
import com.example.exception.DatabaseConstraintException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ExceptionHandling;
import com.example.exception.InvalidUserOrRoleException;
import com.example.repository.AddressRepo;
import com.example.repository.InventoryDetailRepo;
import com.example.repository.InventoryRepo;
import com.example.repository.ProductRepo;
import com.example.repository.RoleRepo;
import com.example.repository.StatusRepo;
import com.example.repository.SupplierRepo;
import com.example.repository.UserRepo;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class InventoryDetailService extends PosService<InventoryDetail, Long> {
	private final InventoryDetailRepo repository;

	public InventoryDetailService(InventoryDetailRepo repository) {
		super(repository);
		this.repository = repository;
	}

	public List<InventoryDetail> findByInventory(Long inventoryId) {
		List<InventoryDetail> inventoryDetails = repository.findByInventory(inventoryId);

		if (inventoryDetails == null)
			throw new NoSuchElementException("No inventory details found by the inventory id " + inventoryId);

		return inventoryDetails;
	}

}
