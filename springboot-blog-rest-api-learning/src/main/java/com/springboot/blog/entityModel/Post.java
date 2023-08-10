package com.springboot.blog.entityModel;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import jakarta.*;


							/*JPA Entity/Model creation */
/*
 * @Data : Lombok annoatation 
 * usgae : no need to create constructor,getter ,setter and toString methods manually 
 */
//@Data
//includes all arg consturctor
@AllArgsConstructor
//includes no argument constructor
@NoArgsConstructor

/*
 * used to map entity/model with mysql database
 */

//@Embeddable
@Entity
@Table(name="posts",
		//table name "posts" unique key constraint added
		uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
	
	@Id
	//To provide primary key generation strategy
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title",nullable = false)
	private String title;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@Column(name = "content",nullable = false)
	private String content;
	
	
	/*
	 * post is present in comment entity
	 * usage of cascade attribute is whenever parent value changes automatically child value changes
	 * orphanRemoval : whenever remove from parent from child also it should get remove 
	 * mappedBy value should be equal to variable declared in Comment class
	 */
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL ,orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

}
