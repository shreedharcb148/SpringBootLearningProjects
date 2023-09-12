package com.schoolmanagement.entity;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="student",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Student {

	@Id
	//To provide primary key generation strategy
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	private String std;
	
	private String s_class;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="student_course_table",
					joinColumns = {@JoinColumn(name="s_id",referencedColumnName = "id")}, //id of student entity
					inverseJoinColumns = {@JoinColumn(name="c_id",referencedColumnName = "id")}) //id of course entity
	
	@JsonManagedReference
	Set<Course> courses;
	
	public Student() {
		super();
	}

	public Student(int id, String name, String std, String s_class, Set<Course> courses) {
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
