package com.harcanjo.forum.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.profile.ProfileRegisterDTO;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	@PostMapping
	public void register(@RequestBody ProfileRegisterDTO data) {
		System.out.println("Data received: " + data);
	}

}
