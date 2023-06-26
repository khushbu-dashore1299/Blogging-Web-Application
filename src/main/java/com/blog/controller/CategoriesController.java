package com.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;

import com.blog.services.CategoryService;






@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	CategoryService categoryService;

	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("id") Integer id) {
		CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto,id);

		return new ResponseEntity<>(updateCategoryDto, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") int id) {
		this.categoryService.deleteCategory(id);
       
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted Sucessfully",true), HttpStatus.OK);
	} 

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allUsers = this.categoryService.getAllCategory();
		
		return ResponseEntity.ok(allUsers);
}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getSingleUser(@PathVariable("id")int id){
		  CategoryDto categoryDto = this.categoryService.getByIdCategory(id);
		
		return ResponseEntity.ok(categoryDto); 
}
}
