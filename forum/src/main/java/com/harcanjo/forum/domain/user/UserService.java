package com.harcanjo.forum.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.profile.Profile;
import com.harcanjo.forum.domain.profile.ProfileRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;

	public UserDetailsDTO createUser(@Valid UserCreationDTO data) {		
		if (userRepository.existsByEmail(data.email())) {
			throw new ValidationException("This e-mail is already been used");
		}
		
		// creationValidators.forEach(v -> v.validate(data));
		
		String encryptedPassword = passwordEncoder.encode(data.password());		
		var user = new User(data, encryptedPassword);
		
		userRepository.save(user);
		
		addProfileToUser(data, user.getId());
		
		return new UserDetailsDTO(user);
	}
	
	// TODO: Needs refactor, needs to be able to do with more than one profile
	// comma separated strings, split, trim
	public void addProfileToUser(@Valid UserCreationDTO data, Long userId) {
		if(data.profiles() != null) {
			System.out.println("User profile: " + data.profiles());
			
			if(profileRepository.findByName(data.profiles()) == null) {
				profileRepository.save(new Profile(data.profiles()));
				System.out.println("Profile '"+ data.profiles() + "' created!");
			}
			
			Profile userProfile = profileRepository.getReferenceByName(data.profiles());
			User user = userRepository.getReferenceById(userId);
			
			user.getProfiles().add(userProfile);
			userRepository.save(user);
		}
		
		if(profileRepository.findByName("New user") == null) {
			profileRepository.save(new Profile("New user"));
			System.out.println("Profile 'New user' created!");
		}
		
		Profile defaultProfile = profileRepository.getReferenceByName("New user");
		User user = userRepository.getReferenceById(userId);
		
		user.getProfiles().add(defaultProfile);
		userRepository.save(user);
	}
}
