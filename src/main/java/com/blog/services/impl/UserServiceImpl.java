package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entites.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userrepo;
	
	@Autowired
    private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userrepo.save(user);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId)  {

		Optional<User> optional = this.userrepo.findById(userId);
		//User user  = this.userrepo.findById(userId).orElseThrow(ResourceNotFoundException("User","id",userId)::new);
		try {
			
			User user = optional.get();

			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());

			User updatedUser = userrepo.save(user);
			return userToDto(updatedUser);
		} catch (Exception e) {
		   
			e.printStackTrace();
		    
		}
		return null;
	}


	@Override
	public UserDto getUserById(Integer userId) {
		Optional<User> optional = this.userrepo.findById(userId);
		try {
			User user = optional.get();

			return userToDto(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> findAll = userrepo.findAll();
		List<UserDto> l2 = new ArrayList<>();
		for (User user : findAll) {
			l2.add(userToDto(user));
		}

		return l2;
	}

	@Override
	public void deleteUser(Integer userId) {
		Optional<User> optional = this.userrepo.findById(userId);
		try {
			User user = optional.get();
			this.userrepo.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());

		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto =  this.modelMapper.map(user,UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());

		return userDto;
	}

	@Override
	public void getAllUser() {
		// TODO Auto-generated method stub
		
	}

}
