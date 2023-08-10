package com.springboot.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.blog.dtos.CommentDto;

public interface CommentService {

	CommentDto createComment(long postId,CommentDto commentDto);
	
	List<CommentDto> getCommentsByPostId(long postId);
	
	CommentDto getCommentById(long postId,long commentId);
	
	CommentDto updateComment(long postId,long commentId,CommentDto commentReq);
	
	void deleteComment(long postId,long commentId);
}
