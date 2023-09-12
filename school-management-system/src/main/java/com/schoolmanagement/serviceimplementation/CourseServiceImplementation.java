package com.schoolmanagement.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.dto.CourseDto;
import com.schoolmanagement.dto.StudentDto;
import com.schoolmanagement.entity.Course;
import com.schoolmanagement.entity.Student;
import com.schoolmanagement.repository.CourseRepository;
import com.schoolmanagement.service.CourseService;

@Service
public class CourseServiceImplementation implements CourseService {

	private CourseRepository courseRepository;

	@Autowired
	public CourseServiceImplementation(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	//convert entity into DTO
	private CourseDto mapToDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setName(course.getName());
		courseDto.setFee(course.getFee());
		courseDto.setNo_of_modules(course.getNo_of_modules());

		return courseDto;
	}

	private Course mapToEntity(CourseDto courseDto) {
		Course course = new Course();
		//student.setS_rollNo(studentDto.getS_rollNo());
		course.setId(courseDto.getId());
		course.setName(courseDto.getName());
		course.setFee(courseDto.getFee());
		course.setNo_of_modules(course.getNo_of_modules());


		return course;
	}

	@Override
	public List<CourseDto> findByFeeLessThan(double fee) {
		List<CourseDto> courses = courseRepository.findByFeeLessThan(fee).stream()
				.map((Course -> mapToDto(Course))).toList();

		return courses;
	}

}
