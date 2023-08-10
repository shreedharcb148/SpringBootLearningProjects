package com.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dtos.CommentDto;
import com.springboot.blog.service.CommentService;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("/api/")
public class CommentController {

	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") long postId,
			@Valid @RequestBody CommentDto commentDto){
		
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value="postId") long postId){
		
		return commentService.getCommentsByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value="postId") long postId,
			@PathVariable(value="commentId")  long commentId){
		
		return new ResponseEntity<CommentDto>(commentService.getCommentById(postId, commentId),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") long postId,
			@PathVariable(value="commentId")  long commentId,
			@Valid @RequestBody CommentDto commentDto){
		
		CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
		
		return new ResponseEntity<CommentDto>(updatedComment,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId") long postId,
			@PathVariable(value="commentId")  long commentId){
		
		commentService.deleteComment(postId, commentId);
		
		return new ResponseEntity<String>("Comment deleted succesfully",HttpStatus.OK);
	}
	
}
