package com.schoolmanagement.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.dto.StudentDto;
import com.schoolmanagement.entity.Student;
import com.schoolmanagement.exceptions.ResourceNotFoundException;
import com.schoolmanagement.repository.CourseRepository;
import com.schoolmanagement.repository.StudentRepository;
import com.schoolmanagement.service.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {


	private StudentRepository studentRepository;

	@Autowired
	public StudentServiceImplementation(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	
	}

	//convert entity into DTO
	private StudentDto mapToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setS_class(student.getS_class());
		studentDto.setStd(student.getStd());
		studentDto.setCourses(student.getCourses());

		return studentDto;
	}

	private Student mapToEntity(StudentDto studentDto) {
		Student student = new Student();
		//student.setS_rollNo(studentDto.getS_rollNo());
		student.setName(studentDto.getName());
		student.setS_class(studentDto.getS_class());
		student.setStd(studentDto.getStd());
		student.setCourses(studentDto.getCourses());
		return student;
	}

	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		//convert DTO to entity

		if(this.studentRepository != null) {
			Student newpost = this.studentRepository.save(mapToEntity(studentDto));
			//convert entity into DTO
			StudentDto postResponse = mapToDto(newpost);
			return postResponse;
		}else {
			return null;
		}
	}

	@Override
	public StudentDto getStudentById(int id) {	
		Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
		return mapToDto(student);
	}

	
	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentDto> students = studentRepository.findAll().stream()
										.map((student -> mapToDto(student))).toList();
		return students;
	}

	@Override
	public String deleteStudentById(int id) {
		Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
		String out=null;
		if(student != null){
			studentRepository.deleteById(id);
			out ="student with id : "+id+" deleted succesfully";
		}else {
			out = "student with id : "+id+" not found";
		}
		
		return out;
	}

	@Override
	public List<StudentDto> findByName(String name) {
		List<StudentDto> students = studentRepository.findByName(name).stream()
				.map((student -> mapToDto(student))).toList();
		
		return students;
	}

	@Override
	public StudentDto updateStudent(int id,StudentDto studentDto) {
		Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
	
		if(student != null){
			student.setCourses(studentDto.getCourses());
			student.setName(studentDto.getName());
			student.setS_class(studentDto.getS_class());
			student.setStd(studentDto.getStd());
			student.setCourses(studentDto.getCourses());
			studentDto = mapToDto(studentRepository.save(student));
		}
		return studentDto;
	}

	public Student getStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
		return student;
	}
	

	

}
