package com.harcanjo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.harcanjo.forum.domain.user.UserCreationDTO;
import com.harcanjo.forum.domain.user.UserDetailsDTO;
import com.harcanjo.forum.domain.user.UserListDTO;
import com.harcanjo.forum.domain.user.UserRepository;
import com.harcanjo.forum.domain.user.UserService;
import com.harcanjo.forum.domain.user.UserUpdateDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@PostMapping
//	@Transactional
//	public ResponseEntity<UserDetailsDTO> addUser(@RequestBody @Valid UserRegisterDTO data, UriComponentsBuilder uriBuilder) {		
//		String encryptedPassword = passwordEncoder.encode(data.password());		
//		var user = new User(data, encryptedPassword);	
//		
//		// TODO: associate to a profile if its not created/informed in the registration
////		if (data.profiles() != null && !data.profiles().isEmpty()) {
////	        Profile specifiedProfile = profileRepository.findByName(data.profile.name())
////	                .orElseThrow(() -> new IllegalArgumentException("Profile not found: " + data.profile.name()));
////	        user.getProfiles().add(specifiedProfile);
////	    } else {
////	        Profile defaultProfile = profileRepository.findByName("New Student")
////	                .orElseGet(() -> profileRepository.save(new profile("New Student")));
////	        user.getProfiles().add(defaultProfile);
////	    }
//		
//		repository.save(user);
//		
//		var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
//	
//		return ResponseEntity.created(uri).body(new UserDetailsDTO(user));
//	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDetailsDTO> addUser(@RequestBody @Valid UserCreationDTO data, UriComponentsBuilder uriBuilder) {		
		var dto = userService.createUser(data);
		
		var uri = uriBuilder.path("/user/{id}").buildAndExpand(dto.id()).toUri();
	
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping
	public ResponseEntity<Page<UserListDTO>> showUserList(Pageable page){
		var pageList =  repository.findAllByActiveTrue(page).map(UserListDTO::new);
		return ResponseEntity.ok(pageList);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDetailsDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO data) {
		var user = repository.getReferenceById(id);
		user.updateUserInformations(data);
		
		return ResponseEntity.ok(new UserDetailsDTO(user));
	}
	
	// Logical Deletion	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//		var user = repository.getReferenceById(id);
//		user.inactivateUser();
//		
//		return ResponseEntity.noContent().build();
//	}

	// TODO: check the need of Deletion From DB or just logical deletion	
	@DeleteMapping("/{id}")
	@Transactional
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDetailsDTO> showUser(@PathVariable Long id) {
		var user = repository.getReferenceById(id);		
		return ResponseEntity.ok(new UserDetailsDTO(user));
	}
	
}
