package com.microservices.rating.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.rating.service.entity.Rating;
import com.microservices.rating.service.services.RatingService;

/*
 * Errors : custom methods are working 
 * rating id is coming as null even it is automatically created by mongodb
 */


@RestController
@RequestMapping("/rating")
public class RatingController {

	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create_rating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createrating(rating));
	}
	
	@GetMapping("/getallratings")
	public ResponseEntity<List<Rating>> getAllratings(){
		return ResponseEntity.ok(ratingService.getAllRatings());
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByuserId(@PathVariable("userId") String id){
		System.out.println("hii");
		return ResponseEntity.ok(ratingService.getRatingByUserId(id));
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByhotelId(@PathVariable("hotelId") String id){
		return ResponseEntity.ok(ratingService.getRatingByHotelId(id));
	}
	
	
	
}
