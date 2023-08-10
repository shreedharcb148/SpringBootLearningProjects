package com.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
//url is called as end points
@RequestMapping("/api/post")
public class PostController {

	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	// create blog post

	@PostMapping("/create")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}

/*	//get all post rest api
//	@GetMapping("/posts")
//	public List<PostDto> getAllPosts(){
//		return postService.getAllPosts();
//	}*/

/*	@GetMapping("/posts")
	public List<PostDto> getAllPosts(
			@RequestParam(value="pageNo",defaultValue = "0", required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required = false) int pageSize) {
		return postService.getAllPosts(pageNo,pageSize);
	}
*/

	
	@GetMapping("/posts")
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir) {
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	
	
	// get post by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));

	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
		return ResponseEntity.ok(postService.updatePost(postDto, id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {

		postService.deletePostById(id);
		return new ResponseEntity<String>("Post entry deleted succesffully", HttpStatus.OK);

	}

}
