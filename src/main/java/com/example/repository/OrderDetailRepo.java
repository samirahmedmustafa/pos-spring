package com.example.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
//	@Query("select od from OrderDetail od where od.order.id = ?1")
	Optional<List<OrderDetail>> findByOrder(Long order);
}
