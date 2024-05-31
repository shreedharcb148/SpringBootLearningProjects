package com.learn.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.learn.models.User;
import com.learn.repository.UserRepository;

public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= this.userRepository.getById(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("not found");
		}
		
		return new CustomerUserDetails(user);
		
	}

}
