package com.microservices.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservices.user.service.entity.Rating;
import com.microservices.user.service.externalservices.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;
	
	@Test
	void createRating() {
		
		Rating rating = new Rating();
		rating.setRating(10);
		rating.setUserId("");
		rating.setHotelId("");
		rating.setFeedback("this is created using feign client");
		ratingService.createRating(rating);
		
		System.out.println("new rating is created");
	}
}
