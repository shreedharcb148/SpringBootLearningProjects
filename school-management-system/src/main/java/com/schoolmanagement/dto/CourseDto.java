package com.schoolmanagement.dto;

import java.util.Set;

import com.schoolmanagement.entity.Student;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

public class CourseDto {
	
	private Long id;
	private String name;
	private int fee;
	private int no_of_modules;

	private Set<Student> students;
	
	public CourseDto() {
		super();
	}

	public CourseDto(Long id, String name, int fee, int no_of_modules, Set<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.no_of_modules = no_of_modules;
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getNo_of_modules() {
		return no_of_modules;
	}

	public void setNo_of_modules(int no_of_modules) {
		this.no_of_modules = no_of_modules;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	
}