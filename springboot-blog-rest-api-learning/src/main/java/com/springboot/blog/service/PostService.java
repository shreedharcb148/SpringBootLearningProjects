package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);
	
	//List<PostDto> getAllPosts();
	PostResponse getAllPosts(int pageNo,int pageSize,String sortBy, String sortDir);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto ,long id);
	
	void deletePostById(long id);
}
