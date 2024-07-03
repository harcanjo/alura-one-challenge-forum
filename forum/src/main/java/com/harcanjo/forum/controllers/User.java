package com.harcanjo.forum.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.user.RegisterUserDTO;

@RestController
@RequestMapping("/user")
public class User {
	
	@PostMapping
	public void register(@RequestBody RegisterUserDTO data) {
		System.out.println(data);
	}

}
