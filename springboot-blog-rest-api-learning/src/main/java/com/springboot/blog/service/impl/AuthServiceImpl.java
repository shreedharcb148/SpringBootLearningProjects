package com.springboot.blog.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.entityModel.Role;
import com.springboot.blog.entityModel.User;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService{
	
	private AuthenticationManager authenticationManager;	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public AuthServiceImpl() {
		super();
	}

	public AuthServiceImpl(AuthenticationManager authenticationManager,
			UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public String login(LoginDto loginDto) {
		
		  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		  
		  if(authentication != null) {
			  SecurityContextHolder.getContext().setAuthentication(authentication); 
		  }else {
			  System.out.println("nulll");
		  }
		 
		  return "User Login Sucessfully";
		
		
	}

	@Override
	public String register(RegisterDto registerDto) {
		
//		//if user already exist in database or not
//		if(userRepository.existsByUsername(registerDto.getUsername())) {
//			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username already exists");
//		}
//		
//		//if email id exists in database or not
//		if(userRepository.existstByEmail(registerDto.getEmail())) {
//			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"email already exists");
//		}
//		
		User user = new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "user registerd successfully";
		
	}

}
