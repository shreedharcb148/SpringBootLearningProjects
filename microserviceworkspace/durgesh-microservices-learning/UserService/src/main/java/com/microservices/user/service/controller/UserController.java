package com.microservices.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.service.entity.User;
import com.microservices.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//create user
	@PostMapping("/create_user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user2 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user2);
	}
	
	//get single user
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
		
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//get all users
	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers(){
		
		return ResponseEntity.ok(userService.getAllUsers());
	}
}
