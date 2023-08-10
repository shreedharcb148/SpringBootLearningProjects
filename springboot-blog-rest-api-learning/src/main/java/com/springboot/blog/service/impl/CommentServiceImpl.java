package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.dtos.CommentDto;
import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.entityModel.Comment;
import com.springboot.blog.entityModel.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFounException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	
	// convert entity into DTO
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());

		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		
		return comment;
	}
	
	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Comment comment = mapToEntity(commentDto);
		
		//retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFounException("Post", "id", postId));
		
		//set post to comment entity
		comment.setPost(post);
		
		//comment entity to db
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
	}


	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		
		//retrive comments by post id
		List<Comment> commnets = commentRepository.findByPostId(postId);
		
		//convert list of comment entities to list of comment dto's
		return commnets.stream().map(comment ->mapToDto(comment)).collect(Collectors.toList());
	}


	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		
		//retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(
			() -> new ResourceNotFounException("Post", "id", postId));
		
		//retreive comment by id
		Comment comment =  commentRepository.findById(commentId).orElseThrow(
				() -> new ResourceNotFounException("Comment", "Id", commentId));
		
		if(!comment.getPost().getId().equals(comment.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_GATEWAY, "Comment doesnot belong to post");
		}
				
		return mapToDto(comment);
	}


	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentReq) {
		
		// retrieve post entity by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFounException("Post", "id", postId));
		
		// retreive comment by id
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFounException("Comment", "Id", commentId));
		
		if(!comment.getPost().getId().equals(comment.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_GATEWAY, "Comment doesnot belong to post");
		}
		
		comment.setId(commentReq.getId());
		comment.setName(commentReq.getName());
		comment.setEmail(commentReq.getEmail());
		comment.setBody(commentReq.getBody());
		
		Comment updatedComment =  commentRepository.save(comment);
		
		return mapToDto(updatedComment);
				
	}


	
	@Override
	public void deleteComment(long postId, long commentId) {
		// retrieve post entity by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFounException("Post", "id", postId));

		// retreive comment by id
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFounException("Comment", "Id", commentId));

		if (!comment.getPost().getId().equals(comment.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_GATEWAY, "Comment doesnot belong to post");
		}
				
		commentRepository.delete(comment);
	}

}
