package com.blog.services;

import java.util.List;

import com.blog.entites.Post;
import com.blog.payloads.PostDto;

public interface PostService {
	
	//create 
	PostDto createPost(PostDto postDto , int userId , int categoryId);
	
  //update
	PostDto updatePost(PostDto postDto , int id);
	
	//delete
	
	void deletePost(Integer id);
	
	//get all
	
	List<PostDto> getAllPost();
	
	//get by id
	
	PostDto getPostById(Integer id);
	
	//get all  by category
	
	List<PostDto> getAllByCategory(Integer catId);
	
	//get all by user
	
	List<PostDto> getAllByUser(Integer userId);
	
	//search post
	
	List<PostDto> searchPost(String keyword);
}
