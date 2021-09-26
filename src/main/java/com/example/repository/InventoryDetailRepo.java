package com.example.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.InventoryDetail;

public interface InventoryDetailRepo extends JpaRepository<InventoryDetail, Long> {
	@Query("select id from InventoryDetail id where id.inventory.id = ?1")
	Optional<List<InventoryDetail>> getByInventory(Long inventory);
}
