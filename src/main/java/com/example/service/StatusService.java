package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Status;
import com.example.repository.StatusRepo;

@Service
@Transactional
public class StatusService extends AbstractService<Status, Integer> {

	public StatusService(StatusRepo repository) {
		super(repository);
	}
}
