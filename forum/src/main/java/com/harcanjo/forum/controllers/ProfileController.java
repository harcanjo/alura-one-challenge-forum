package com.harcanjo.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.harcanjo.forum.profile.Profile;
import com.harcanjo.forum.profile.ProfileDetailsDTO;
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
	public ResponseEntity<ProfileDetailsDTO> addProfile(@RequestBody @Valid ProfileRegisterDTO data, UriComponentsBuilder uriBuilder) {
		var profile = new Profile(data);
		repository.save(profile);
		
		var uri = uriBuilder.path("/profiles/{id}").buildAndExpand(profile.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProfileDetailsDTO(profile));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProfileListDTO>> showProfileList(Pageable page){
		var pageList = repository.findAllByActiveTrue(page).map(ProfileListDTO::new);		
		return ResponseEntity.ok(pageList);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<ProfileDetailsDTO> updateProfile(@RequestBody @Valid ProfileUpdateDTO data) {
		var profile = repository.getReferenceById(data.id());
		profile.updateProfileInformations(data);
		
		return ResponseEntity.ok(new ProfileDetailsDTO(profile));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
		var profile = repository.getReferenceById(id);
		profile.inactivateProfile();
		
		return ResponseEntity.noContent().build();
	}	
	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void deleteProfile(@PathVariable Long id) {
//		repository.deleteById(id);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfileDetailsDTO> showUser(@PathVariable Long id) {
		var profile = repository.getReferenceById(id);		
		return ResponseEntity.ok(new ProfileDetailsDTO(profile));
	}
	
}
