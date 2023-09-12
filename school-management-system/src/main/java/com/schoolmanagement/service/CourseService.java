package com.schoolmanagement.service;

import java.util.List;

import com.schoolmanagement.dto.CourseDto;

public interface CourseService {

	List<CourseDto> findByFeeLessThan(double fee);
}
