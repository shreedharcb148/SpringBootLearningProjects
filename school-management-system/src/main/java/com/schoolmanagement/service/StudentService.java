package com.schoolmanagement.service;

import java.util.List;

import com.schoolmanagement.dto.StudentDto;
import com.schoolmanagement.entity.Student;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    
    StudentDto getStudentById(int id);
	
    List<StudentDto> getAllStudents();
    
    String deleteStudentById(int id);
    
    List<StudentDto> findByName(String name);
    
    StudentDto updateStudent(int id,StudentDto studentDto);
    
    //Student getStudent(int id);
    
    
}
