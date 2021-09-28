package com.example.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.StatusRepo;
import com.example.service.AbstractService;
import com.example.service.StatusService;

@RestController
@RequestMapping("statuses")
public class StatusController extends AbstractController<Status, Integer> {

	public StatusController(StatusService service) {
		super(service);
	}
}
