package com.microservices.user.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.user.service.entity.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.repository.UserRepository;
import com.microservices.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
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
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElseThrow(()->
					new ResourceNotFoundException("User with given id is not found on server...!!! : "+userId));
	}

}
