package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Role;
import com.example.repository.RoleRepo;

@Service
@Transactional
public class RoleService extends AbstractService<Role, Long> {

	public RoleService(RoleRepo repository) {
		super(repository);
	}
}
