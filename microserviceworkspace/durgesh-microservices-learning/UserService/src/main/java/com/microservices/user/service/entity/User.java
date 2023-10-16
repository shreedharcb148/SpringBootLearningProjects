package com.microservices.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Entity
@Table(name="micro_users")
public class User {

	@Id
	@Column(name="ID")
	private String userId;
	
	@Column(name="NAME",length = 20)
	private String name;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="ABOUT")
	private String about;
	
	@Column(name="info")
	private String info;
	
	//it will not store/save in database
	@Transient
	//@Column(name="RATINGS")
	private List<Rating> ratings = new ArrayList<>();
	
	

	public User() {
		super();
	}

	public User(String userId, String name, String email, String about,String info) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.about = about;
		this.info = info;
		//this.ratings = ratings;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
	
}
