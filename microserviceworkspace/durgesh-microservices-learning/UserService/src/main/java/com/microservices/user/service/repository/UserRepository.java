package com.microservices.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	
}
