package com.sb.mockito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.mockito.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	List<User> findByAddress(String address);
}
