package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	@Query("select r from Role r where r.name = ?1")
	Optional<Role> getByName(String name);
}
