package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.entityModel.Post;
import com.springboot.blog.exception.ResourceNotFounException;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	
	private PostRepository postRepository;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}
	
	//convert entity into DTO
	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		postDto.setTitle(post.getTitle());
		postDto.setId(post.getId());
		
		return postDto;
	}
	
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		return post;
	}
	
	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		
		//convert DTO to entity
		Post newpost = postRepository.save(mapToEntity(postDto));
		//convert entity into DTO
		PostDto postResponse = mapToDto(newpost);
		return postResponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
		
		
		//if sortDir is asc sort in ascending order else descending order
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		
		//create pageRequest object and pass that to findall method
		PageRequest pageable = PageRequest.of(pageNo, pageSize,sort);
		Page<Post> posts = postRepository.findAll(pageable);
		//get content for page object
		List<Post> listOfPosts = posts.getContent();
		
		//return listOfPosts.stream().map(post ->mapToDto(post)).collect(Collectors.toList());
		
		List<PostDto> content = listOfPosts.stream().map(post ->mapToDto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}
	
/*//	public List<PostDto> getAllPosts() {
//		// TODO Auto-generated method stub
//		
//		List<Post> posts = postRepository.findAll();
//		return posts.stream().map(post ->mapToDto(post)).collect(Collectors.toList());
//	}*/

	@Override
	public PostDto getPostById(long id) {
		
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFounException("Post","id",id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		
		//get post by id from database
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFounException("Post","id",id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}
	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFounException("Post","id",id));
		postRepository.delete(post);
		
	}
	

	

}
