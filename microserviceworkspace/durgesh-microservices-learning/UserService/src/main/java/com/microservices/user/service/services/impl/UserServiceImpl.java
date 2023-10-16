package com.microservices.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		//http://localhost:8082/rating/getratingbyuserid/0a084e9d-8657-48b6-b765-f38c116e8626
		
		ArrayList<?> forObject =  restTemplate.getForObject("http://localhost:8082/rating/getratingbyuserid/0a084e9d-8657-48b6-b765-f38c116e8626",
				ArrayList.class);
		logger.info("abcccccccccc");
		logger.info("{}",forObject);
		
		return user;
	}

	@Override
	public User findByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

}
