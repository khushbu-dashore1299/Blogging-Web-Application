package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entites.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		  Category category = this.modelMapper.map(categoryDto, Category.class);
		                Category save = categoryRepo.save(category);
		                CategoryDto cat = this.modelMapper.map(save, CategoryDto.class);
		                return cat;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		    Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "Category Id",categoryId)); 
    
		    cat.setCategoryTitle(categoryDto.getCategoryTitle());
		    cat.setCategoryDescription(categoryDto.getCategoryDescription());
	           Category save = categoryRepo.save(cat);
	           CategoryDto catDto = this.modelMapper.map(save,CategoryDto.class);
		       
	       return catDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		 Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "Category Id",categoryId)); 
		 this.categoryRepo.deleteById(categoryId);
		 }

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
	     List<CategoryDto> catDto = categories.stream().map((cat) ->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}

	@Override
	public CategoryDto getByIdCategory(Integer categoryId) {
		 Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "Category Id",categoryId)); 
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

}
