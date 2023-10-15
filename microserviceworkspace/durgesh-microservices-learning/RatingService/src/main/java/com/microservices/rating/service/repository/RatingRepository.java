package com.microservices.rating.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.rating.service.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{

	//custom methods
	
	List<Rating> findByuserId(String userId);
	
	List<Rating> findByhotelId(String hotelId);
}
