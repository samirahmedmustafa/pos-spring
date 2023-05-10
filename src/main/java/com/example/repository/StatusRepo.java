package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Status;

public interface StatusRepo extends JpaRepository<Status, Long> {

}