package com.harcanjo.forum.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.profile.Profile;
import com.harcanjo.forum.domain.profile.ProfileDetailsDTO;
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
		
		String encryptedPassword = passwordEncoder.encode(data.password());		
		var user = new User(data, encryptedPassword);
		
		userRepository.save(user);
		
		addProfileToUser(data, user.getId());
		return getUserById(user.getId());
	}
	
	// TODO: Needs refactor
	public void addProfileToUser(@Valid UserCreationDTO data, Long userId) {
		
		if (!data.profiles().isBlank()) {
		    String[] profileNames = data.profiles().trim().split("\\s*,\\s*");
		    
		    for (String profileName : profileNames) {
		        System.out.println("User profile: " + profileName);
		        
		        if (profileRepository.findByName(profileName) == null) {
		            profileRepository.save(new Profile(profileName));
		            System.out.println("Profile '" + profileName + "' created!");
		        }
		        
		        Profile userProfile = profileRepository.getReferenceByName(profileName);
		        User user = userRepository.getReferenceById(userId);
		        
		        user.getProfiles().add(userProfile);
		        userRepository.save(user);
		    }
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
	
	// TODO: Needs Refactor
	public UserDetailsDTO updateUser(Long id, UserUpdateDTO data, @AuthenticationPrincipal User loggedUser) {
		if(userRepository.getReferenceById(id) != userRepository.getReferenceById(loggedUser.getId())) {
			throw new ValidationException("You can't edit other user");
		}
		
		var user = userRepository.getReferenceById(id);
		
		if(!data.profiles().isBlank()) {
			user.getProfiles().clear();
			
			String[] profileNames = data.profiles().trim().split("\\s*,\\s*");
					    
		    for (String profileName : profileNames) {
		        System.out.println("User profile: " + profileName);
		        
		        if (profileRepository.findByName(profileName) == null) {
		            profileRepository.save(new Profile(profileName));
		            System.out.println("Profile '" + profileName + "' created!");
		        }
		        
		        Profile userProfile = profileRepository.getReferenceByName(profileName);
		        
		        user.getProfiles().add(userProfile);
		        userRepository.save(user);
		    }
		}
		
		if(!data.password().isBlank()) {
			var newPassword = passwordEncoder.encode(data.password());	
			System.out.println("Tem senha: " + newPassword);
			user.updateUserPassword(newPassword);
		}		
		
		user.updateUserInformations(data);	
		
		return getUserById(user.getId());
	}
	
	// Show user with profiles
	public UserDetailsDTO getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ValidationException("User id entered does not exist"));
		
		List<ProfileDetailsDTO> profileDTO = user.getProfiles().stream()
				.map(this::mapProfileToUserDTO)
				.collect(Collectors.toList());
		
		return new UserDetailsDTO(user, profileDTO);
	}
	
	private ProfileDetailsDTO mapProfileToUserDTO(Profile profile) {
		return new ProfileDetailsDTO(profile);
	}
}
