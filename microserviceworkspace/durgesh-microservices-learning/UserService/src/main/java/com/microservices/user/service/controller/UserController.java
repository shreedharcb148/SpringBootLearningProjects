package com.microservices.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.service.entity.User;
import com.microservices.user.service.services.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(getClass());
	// create user
	@PostMapping("/create_user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user2 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user2);
	}

	// get single user
	@GetMapping("/getuser/{userId}")
	//@CircuitBreaker(name="ratingHotelBreaker" ,fallbackMethod = "ratingHotelFallBack")
	//@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId) {
		
		logger.info("Get SIngle User Handler : userController");
		logger.info("Retry count : {}",retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	// get all users
	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/getuserbyid/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
		User user = userService.findByUserId(userId);
		return ResponseEntity.ok(user);
	}
	
//	@DeleteMapping("/delete_user/{userId}")
//	public User delteuserById(@PathVariable("userId") String id) {
//		return userService.deleteByUserId(id);
//	}
//	
	
	//create fall back method for circuitbreaker
	//return type should be same as calling api
	
	
	int retryCount=1;
	public ResponseEntity<User> ratingHotelFallBack(String userId,Exception ex){
		
		logger.info("Fallback is executed service is down : ",ex.getMessage());
		
		User user = new User();user.setEmail("dummy@gmail.com");user.setName("dummy");
		user.setAbout("This user is created dummy because some service is down");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
