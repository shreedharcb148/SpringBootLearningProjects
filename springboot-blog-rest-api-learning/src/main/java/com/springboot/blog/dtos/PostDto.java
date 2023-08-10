package com.springboot.blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//@Data
public class PostDto {

	
	private long id;
	
	@NotEmpty
	@Size(min=2,message = "Post title should have at leaset 2 charecters")
	private String title;
	
	@NotEmpty
	@Size(min=10,message = "Post title should have at leaset 10 charecters")
	private String description;
	
	@NotEmpty
	private String content;
	
	public PostDto() {
		super();
	}
	
	public PostDto(long id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
