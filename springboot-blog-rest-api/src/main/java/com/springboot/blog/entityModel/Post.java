package com.springboot.blog.entityModel;


/*
 * @Data : Lombok annoatation 
 * usgae : no need to create constructor,getter ,setter and toString methods manually 
 */

public class Post {

	private Long id;
	private String title;
	private String description;
	private String content;
	
	
	public Post() {
		super();
	}
	
	public Post(Long id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}
	
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

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + "]";
	}
	
	
	
	
	
	
}
