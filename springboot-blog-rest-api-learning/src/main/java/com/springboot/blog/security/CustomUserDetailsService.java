//package com.springboot.blog.security;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.springboot.blog.entityModel.User;
//import com.springboot.blog.repository.UserRepository;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	UserRepository userRepository;
//	
//	@Autowired
//	public CustomUserDetailsService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	@Override
//	public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {
//		
//		User user = userRepository.findUserByNameOrEmail(usernameorEmail, usernameorEmail)
//					.orElseThrow(()->
//						new UsernameNotFoundException("User not found with username or email" +usernameorEmail));
//		
//		
//		//converting set of roles into set of granted authorities
//		Set<SimpleGrantedAuthority> authorities = user
//				.getRoles()
//				.stream()
//				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//		
//		//converting user object into spring provided security object	
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
//	
//	
//	}
//
//}
