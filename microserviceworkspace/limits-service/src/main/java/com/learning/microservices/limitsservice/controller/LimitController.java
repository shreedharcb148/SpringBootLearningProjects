package com.learning.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.limitsservice.bean.Limits;
import com.learning.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration configuration;
	
	public LimitController() {
	}
	
	@GetMapping("/limits")
	public Limits retriveLimits() {
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}
