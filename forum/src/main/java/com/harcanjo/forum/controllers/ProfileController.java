package com.harcanjo.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.profile.Profile;
import com.harcanjo.forum.profile.ProfileRegisterDTO;
import com.harcanjo.forum.profile.ProfileRepository;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileRepository repository;
	
	@PostMapping
	public void register(@RequestBody ProfileRegisterDTO data) {
		repository.save(new Profile(data));
	}

}
