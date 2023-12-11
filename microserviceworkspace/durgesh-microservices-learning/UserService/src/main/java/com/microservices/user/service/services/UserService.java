package com.microservices.user.service.services;

import java.util.List;

import com.microservices.user.service.entity.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUsers();
	
	User getUser(String userId);
	
	User findByUserId(String userId);
	
	//User deleteByUserId(String userId);
	
	//TODO : delete 
	//TODO : update
}
