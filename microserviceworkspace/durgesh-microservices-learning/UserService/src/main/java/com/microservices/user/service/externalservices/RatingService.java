package com.microservices.user.service.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.microservices.user.service.entity.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//get rating
	@GetMapping("/rating/users/{userId}")
	List<Rating> getRatingByUserId(@PathVariable("userId") String id);
	
	//post rating
	@PostMapping("/rating/create_rating")
	Rating createRating(Rating rating);
	
	//put rating
	@PutMapping("/rating/updateRating/{ratingId}")
	Rating updateRating(@PathVariable String ratingId, Rating rating);
	
	//delete rating
	@DeleteMapping("/rating/deleteRating/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
	
}
