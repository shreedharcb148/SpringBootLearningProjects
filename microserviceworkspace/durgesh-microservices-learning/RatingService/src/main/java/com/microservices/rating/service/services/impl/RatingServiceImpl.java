package com.microservices.rating.service.services.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return  ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String StudentId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByuserId(StudentId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByhotelId(hotelId);
	}

}
