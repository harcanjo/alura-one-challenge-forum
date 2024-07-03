package com.harcanjo.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.user.RegisterUserDTO;
import com.harcanjo.forum.user.User;
import com.harcanjo.forum.user.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping
	@Transactional
	public void register(@RequestBody RegisterUserDTO data) {
		repository.save(new User(data));
	}

}
