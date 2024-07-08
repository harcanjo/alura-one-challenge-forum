package com.harcanjo.forum.domain.profile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.user.User;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfileService {
	
	@Autowired 
	private ProfileRepository profileRepository;
	
	public void deleteProfileById(Long profileId) {
        Optional<Profile> profileOptional = profileRepository.findById(profileId);
        
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();

            // Remove associations in the user_profile table
            for (User user : profile.getUsers()) {
                user.getProfiles().remove(profile);
            }

            // Clear the associations from the Profile side
            profile.getUsers().clear();

            // Now delete the profile
            profileRepository.delete(profile);
        } else {
            // Handle case where profile with given ID is not found
            throw new EntityNotFoundException("Profile with id " + profileId + " not found");
        }
    }

}
