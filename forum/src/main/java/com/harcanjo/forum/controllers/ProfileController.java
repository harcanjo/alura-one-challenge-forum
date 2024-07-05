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

import com.harcanjo.forum.profile.Profile;
import com.harcanjo.forum.profile.ProfileListDTO;
import com.harcanjo.forum.profile.ProfileRegisterDTO;
import com.harcanjo.forum.profile.ProfileRepository;
import com.harcanjo.forum.profile.ProfileUpdateDTO;

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
	public Page<ProfileListDTO> showProfileList(Pageable page){
		return repository.findAllByActiveTrue(page).map(ProfileListDTO::new);
	}
	
	@PutMapping
	@Transactional
	public void updateProfile(@RequestBody @Valid ProfileUpdateDTO data) {
		var profile = repository.getReferenceById(data.id());
		profile.updateProfileInformations(data);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deleteProfile(@PathVariable Long id) {
		var profile = repository.getReferenceById(id);
		profile.inactivateProfile();
	}	
	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void deleteProfile(@PathVariable Long id) {
//		repository.deleteById(id);
//	}
	
}
