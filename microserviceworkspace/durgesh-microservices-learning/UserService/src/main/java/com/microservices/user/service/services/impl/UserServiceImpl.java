package com.microservices.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.user.service.entity.Hotel;
import com.microservices.user.service.entity.Rating;
import com.microservices.user.service.entity.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.externalservices.HotelService;
import com.microservices.user.service.externalservices.RatingService;
import com.microservices.user.service.repository.UserRepository;
import com.microservices.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	@Override
	public User saveUser(User user) {
		
		//generate unique id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId); 
		
		return userRepository.save(user);
	}

	@Override
	
	public List<User> getAllUsers() {
		
		//get all users from database
//		List<User> users = userRepository.findAll();
//		//implement rating service call using rest template
//		@SuppressWarnings("unchecked")
//		ArrayList<Rating> ratingsOfUser =  restTemplate.getForObject("http://localhost:8082/rating/getallratings",
//				ArrayList.class);
//		for(User user : users) {
//			for(Rating rating:ratingsOfUser) {
//				if(user.getUserId().equals(rating.getUserId())) {
//					user.getRatings().add(rating);
//				}
//			}
//		}
//		return users;
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		//get user from database
		User user = userRepository.findById(userId).orElseThrow(()->
					new ResourceNotFoundException("User with given id is not found on server...!!! : "+userId));
		
		//fetch rating of the above user from RATING SERVICE
		//working url
		//http://localhost:8082/rating/users/4fad34bd-ea80-45f6-9ac2-a8d3c15bc4c6
		
		@SuppressWarnings("unchecked")
//		Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(),
//				Rating[].class);
		
		List<Rating> ratingsOfUser = ratingService.getRatingByUserId(user.getUserId());
		
		//List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		//logger.info("{}    :  ",ratingsOfUser);
		
		List<Rating> ratingList = new ArrayList<>();
		for(Rating rating : ratingsOfUser) { 
			//api call to hotel service
			
		
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/gethotel/"+rating.getHotelId(), 
//					Hotel.class);
//			Hotel hotel = forEntity.getBody();
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//set the hotel to rating
			rating.setHotel(hotel);
			
			//return rating
			ratingList.add(rating);
			
			//logger.info("response status code : {} ",forEntity.getStatusCode());
		}
//		List<Rating> ratingList = Arrays.stream(ratingsOfUser).toList().stream().map(rating -> {
//			//http://localhost:8081/hotels/gethotel/1d37e0b1-dd56-439f-8c20-14e41056fd7a
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8081/hotels/gethotel/"+rating.getHotelId(), 
//					Hotel.class);
//			
//			Hotel hotel = forEntity.getBody();
//			
//			rating.setHotel(hotel);
//			
//			logger.info("response status code : {} ",forEntity.getStatusCode());
//		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		
		return user;
	}

	@Override
	public User findByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

}
