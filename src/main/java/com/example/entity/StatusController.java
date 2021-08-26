package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.StatusRepo;

@RestController
@RequestMapping("statuses")
public class StatusController extends AbstractController<StatusRepo, Status,  Integer> {

	public StatusController(StatusRepo r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
}
