package com.schoolmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.dto.StudentDto;
import com.schoolmanagement.entity.Student;

@Repository
@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, Integer>  {

	List<Student> findByName(String name);
	
	Student findFirstByName(String name);
	
	Student findLastByName(String name);

}
