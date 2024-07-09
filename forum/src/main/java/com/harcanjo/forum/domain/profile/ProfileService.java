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


            for (User user : profile.getUsers()) {
                user.getProfiles().remove(profile);
            }


            profile.getUsers().clear();
            profileRepository.delete(profile);
        } else {
            throw new EntityNotFoundException("Profile with id " + profileId + " not found");
        }
    }

}
