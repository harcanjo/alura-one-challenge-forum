package com.harcanjo.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.user.User;
import com.harcanjo.forum.user.UserListDTO;
import com.harcanjo.forum.user.UserRegisterDTO;
import com.harcanjo.forum.user.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping
	@Transactional
	public void addUser(@RequestBody @Valid UserRegisterDTO data) {
		repository.save(new User(data));
	}

	@GetMapping
	public List<UserListDTO> showUserList(){
		return repository.findAll().stream().map(UserListDTO::new).toList();
	}
}
