package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.entityModel.Post;

//no need to add bcz
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
