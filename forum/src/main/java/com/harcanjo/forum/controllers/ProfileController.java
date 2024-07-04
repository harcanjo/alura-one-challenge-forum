package com.harcanjo.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.profile.Profile;
import com.harcanjo.forum.profile.ProfileListDTO;
import com.harcanjo.forum.profile.ProfileRegisterDTO;
import com.harcanjo.forum.profile.ProfileRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileRepository repository;
	
	@PostMapping
	@Transactional
	public void addProfile(@RequestBody @Valid ProfileRegisterDTO data) {
		repository.save(new Profile(data));
	}
	
	@GetMapping
	public List<ProfileListDTO> showProfileList(){
		return repository.findAll().stream().map(ProfileListDTO::new).toList();
	}

}
