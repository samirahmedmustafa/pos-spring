package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Neighbourhood;

public interface NeighbourhoodRepo extends JpaRepository<Neighbourhood, Integer> {

}
