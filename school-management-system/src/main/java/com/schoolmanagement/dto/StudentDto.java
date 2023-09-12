package com.schoolmanagement.dto;

import java.util.Set;

import com.schoolmanagement.entity.Course;

import jakarta.persistence.Column;

public class StudentDto {

	private int id;
	
	private String name;
	
	private String std;
	
	private String s_class;
	
	Set<Course> courses;

	public StudentDto() {
		super();
	}

	public StudentDto(int id, String name, String std, String s_class, Set<Course> courses) {
		super();
		this.id = id;
		this.name = name;
		this.std = std;
		this.s_class = s_class;
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public String getS_class() {
		return s_class;
	}

	public void setS_class(String s_class) {
		this.s_class = s_class;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	
	
	
}
