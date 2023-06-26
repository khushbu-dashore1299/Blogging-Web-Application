package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entites.Category;
import com.blog.entites.Comment;
import com.blog.entites.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "PostId",postId));
		Comment comment = this.modelMapper.map(commentDto , Comment.class);
		
		comment.setPost(post);
		Comment save = this.commentRepo.save(comment);
		CommentDto newcomment = this.modelMapper.map(save,CommentDto.class);
		return newcomment;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment cat= this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment" , "Comment Id",commentId)); 
		 this.commentRepo.deleteById(commentId);
		
	}

	
	
}
