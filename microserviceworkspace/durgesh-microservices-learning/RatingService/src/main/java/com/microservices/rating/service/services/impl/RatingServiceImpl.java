package com.microservices.rating.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.rating.service.entity.Rating;
import com.microservices.rating.service.repository.RatingRepository;
import com.microservices.rating.service.services.RatingService;


@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createrating(Rating rating) {
		
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return  ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}

	@Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
        //return ratingRepository.getByUserId(userId);
    }

	@Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

}
