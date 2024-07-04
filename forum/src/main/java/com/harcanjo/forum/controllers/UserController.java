package com.harcanjo.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.user.User;
import com.harcanjo.forum.user.UserListDTO;
import com.harcanjo.forum.user.UserRegisterDTO;
import com.harcanjo.forum.user.UserRepository;
import com.harcanjo.forum.user.UserUpdateDTO;

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
	public Page<UserListDTO> showUserList(Pageable page){
		return repository.findAll(page).map(UserListDTO::new);
	}
	
	@PutMapping
	@Transactional
	public void updateUser(@RequestBody @Valid UserUpdateDTO data) {
		var user = repository.getReferenceById(data.id());
		user.updateUserInformations(data);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
