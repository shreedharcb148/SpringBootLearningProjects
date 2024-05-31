package com.db.repo2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.entity2.Teacher;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}