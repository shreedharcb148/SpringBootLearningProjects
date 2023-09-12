package com.schoolmanagement.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="course",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private int fee;
	
	private int no_of_modules;

	//value for mappedBy attribute should be equal variable used/declared in owner entity here it is Student entity
	@ManyToMany(mappedBy = "courses" ,fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Student> students;
	
	public Course() {
		super();
	}

	public Course(Long id, String name, int fee, int no_of_modules, Set<Student> students) {
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
