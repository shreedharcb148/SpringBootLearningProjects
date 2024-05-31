package com.learn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.models.User;
import com.learn.services.UserService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public List<User> getAllUsers(){
		return this.userService.findAll();
	}
	
	
	@RequestMapping(value="/{un}",method = RequestMethod.GET)
	public User getUserByName(@PathVariable("un") String username) {
		return this.userService.getById(username);
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST)
	public User add(@RequestBody User user) {
		return this.userService.save(user);
	}
}
