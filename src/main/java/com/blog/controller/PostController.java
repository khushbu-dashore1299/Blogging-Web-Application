package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;


@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable("userId") int userId , @PathVariable("categoryId") int categoryId) {
		PostDto createPostDto = this.postService.createPost(postDto , userId , categoryId);

		return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser( @PathVariable("userId") Integer userId ) {
		List<PostDto> postDto = this.postService.getAllByUser(userId);

		
      return  new ResponseEntity<>(postDto ,  HttpStatus.OK);
}
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory( @PathVariable("categoryId") Integer catId ) {
		List<PostDto> postDto = this.postService.getAllByCategory(catId);

		
      return  new ResponseEntity<>(postDto ,  HttpStatus.OK);
}
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost( @PathVariable("postId") Integer postId ) {
		
		this.postService.deletePost(postId);
      return  new ApiResponse("post is sucessfully deleted",true);
}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") Integer id) {
		PostDto updatePostDto = this.postService.updatePost(postDto,id);

		return new ResponseEntity<>(updatePostDto, HttpStatus.OK);
	}
	
	
}