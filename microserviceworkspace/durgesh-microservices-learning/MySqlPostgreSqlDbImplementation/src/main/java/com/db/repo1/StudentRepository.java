package com.db.repo1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.entity1.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}