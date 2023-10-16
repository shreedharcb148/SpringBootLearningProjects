package com.microservices.rating.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.rating.service.entity.Rating;

@Repository
public interface RatingRepository extends  JpaRepository<Rating, String>{ //MongoRepository<Rating, String>{

	//custom methods
	List<Rating> findByUserId(String userId);
	
    List<Rating> findByHotelId(String hotelId);
    
    List<Rating> getByUserId(String userId);
}
