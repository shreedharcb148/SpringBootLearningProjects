package com.learning.microservices.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = 
			LoggerFactory.getLogger(getClass());
			
	@GetMapping("/sample-api")
	
	//by default retry will try 3 times ,if there is any failure means method will be invoked 3 times
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String sampleAPI() {
		
		logger.info("Sample API Call recieved...");
	    ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http:8080/some-dummy-url", 
				String.class);
		return forEntity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
