package com.schoolmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanagement.dto.CourseDto;
import com.schoolmanagement.dto.StudentDto;
import com.schoolmanagement.service.CourseService;
import com.schoolmanagement.service.StudentService;


@RestController
@RequestMapping("/student/")
public class StudentController {

	private StudentService studentService;
	
	private CourseService courseService;
	
	@Autowired
	public StudentController(StudentService studentService,CourseService courseService) {
		super();
		this.studentService = studentService;
		this.courseService  = courseService;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
		return new ResponseEntity<StudentDto>(this.studentService.createStudent(studentDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getstudent/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") int studentId) {
		return new ResponseEntity<StudentDto>(this.studentService.getStudentById(studentId), HttpStatus.FOUND);
		
	}
	
	@GetMapping("/getallstudents")
	public List<StudentDto> getAllStudents() {
		return this.studentService.getAllStudents();
		
	}
	@GetMapping("/deletestudentbyid")
	public String deleteStudentById(@RequestParam int id) {
		return this.studentService.deleteStudentById(id);
	}
	
	@GetMapping("find/{name}")
	public List<StudentDto> findByName(@PathVariable String name){
		return studentService.findByName(name);
	}
	
	@GetMapping("/search/{price}")
	public List<CourseDto> findCourseLessThanPrice(@PathVariable double price){
		return courseService.findByFeeLessThan(price);
	}
	
	@PutMapping("/update/{id}")
	public StudentDto updateStudent(@PathVariable("id") int id,@RequestBody StudentDto studentDto) {
		return studentService.updateStudent(id,studentDto);
	}
	
//	@GetMapping("/getstudent/{id}")
//	public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
//		return new ResponseEntity<Student>(this.studentService.getStudent(id), HttpStatus.FOUND);
//	}
	
	
}
