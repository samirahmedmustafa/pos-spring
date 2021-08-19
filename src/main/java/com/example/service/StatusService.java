package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Status;
import com.example.exception.ItemNotFoundException;
import com.example.repository.StatusRepo;

@Service
@Transactional
public class StatusService {

	private StatusRepo statusRepo;

	public StatusService(StatusRepo statusRepo) {
		super();
		this.statusRepo = statusRepo;
	}

	public List<Status> getStatuses() {
		return statusRepo.findAll();
	}

	public Status getStatusById(Integer id) {
		Status status = statusRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Status with the id %d", id)));
		return status;
	}

	public Status save(Status status) {
		Status savedStatus = statusRepo.save(status);
		return savedStatus;
	}

	public Status update(Status status, Integer id) {
		Status existingStatus = statusRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(String.format("Couldn't find Status with the id %d", id)));
		Status updatedStatus = updateStatus(existingStatus, status);
		return statusRepo.save(updatedStatus);
	}

	public void deleteById(Integer id) {
		statusRepo.deleteById(id);
	}

	private Status updateStatus(Status existingStatus, Status status) {
		existingStatus.setName(status.getName());
		existingStatus.setOrderDetail(status.getOrderDetail());
		return existingStatus;
	}

}
