package com.schoolmanagement.payload;

import java.util.List;

import com.schoolmanagement.dto.StudentDto;

public class StudentResponse {

	private List<StudentDto> content;

	public StudentResponse() {
		super();
	}

	public StudentResponse(List<StudentDto> content) {
		super();
		this.content = content;
	}

	public List<StudentDto> getContent() {
		return content;
	}

	public void setContent(List<StudentDto> content) {
		this.content = content;
	}
	
	
	
}
