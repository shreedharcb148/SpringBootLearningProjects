package com.microservices.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.user.service.entity.Rating;
import com.microservices.user.service.entity.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.repository.UserRepository;
import com.microservices.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public User saveUser(User user) {
		
		//generate unique id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId); 
		
		return userRepository.save(user);
	}

	@Override
	
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		//get user fro database
		User user = userRepository.findById(userId).orElseThrow(()->
					new ResourceNotFoundException("User with given id is not found on server...!!! : "+userId));
		
		//fetch rating of the above userfrom RATING SERVICE
		//working url
		//http://localhost:8082/rating/users/4fad34bd-ea80-45f6-9ac2-a8d3c15bc4c6
		
		@SuppressWarnings("unchecked")
		ArrayList<Rating> ratingsOfUser =  restTemplate.getForObject("http://localhost:8082/rating/users/"+user.getUserId(),
				ArrayList.class);
		logger.info("{}    :  ",ratingsOfUser);
		user.setRatings(ratingsOfUser);
		
		return user;
	}

	@Override
	public User findByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

}
