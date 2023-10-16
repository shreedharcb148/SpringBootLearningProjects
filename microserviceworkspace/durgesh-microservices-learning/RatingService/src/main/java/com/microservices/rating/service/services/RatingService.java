package com.microservices.rating.service.services;

import java.util.List;

import com.microservices.rating.service.entity.Rating;

public interface RatingService {

	Rating createrating(Rating rating);
	
	List<Rating> getAllRatings();
	
	List<Rating> getRatingByUserId(String userId);
				 
	List<Rating> getRatingByHotelId(String hotelId);

}
