package com.microservices.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.user.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByUserId(String userId);
	
	void deleteByUserId(String userId);
}
