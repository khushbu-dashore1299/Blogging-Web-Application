package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entites.Category;
import com.blog.entites.Post;
import com.blog.entites.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {

	

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto , int userId , int  categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "user id " , userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "category id " , categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
		post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category); 
        
		Post save = postRepo.save(post);
        
        PostDto newpost = this.modelMapper.map(save, PostDto.class);
        return newpost;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		Post post= this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post" , "Post Id",id)); 
		    post.setTitle(postDto.getTitle());
		    post.setContent(postDto.getContent());
	           Post save = postRepo.save(post);
	           PostDto newpostDto = this.modelMapper.map(save,PostDto.class);
		       
	       return newpostDto;
	}

	@Override
	public void deletePost(Integer id) {
		Post post= this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post" , "Post Id",id)); 
		 this.postRepo.deleteById(id);
		
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto getPostById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "category id " , (int)categoryId));
		         List<Post> post = this.postRepo.findByCategory(category);
		          List<PostDto> postDto=  post.stream().map((p) -> this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
		          //List<CategoryDto> catDto = categories.stream().map((cat) ->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		  		//return catDto;
		          return postDto;
	}

	@Override
	public List<PostDto> getAllByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "user id " , userId));
        List<Post> post = this.postRepo.findByUser(user);
         List<PostDto> postDto=  post.stream().map((p) -> this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
         //List<CategoryDto> catDto = categories.stream().map((cat) ->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
 		//return catDto;
         return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


}
