package com.harcanjo.forum.domain.user;

import java.util.List;

import com.harcanjo.forum.domain.profile.ProfileDetailsDTO;

public record UserDetailsDTO(Long id, String name, String email, List<ProfileDetailsDTO> profiles) {
	
//	public UserDetailsDTO(User user) {
//		this(user.getId(), user.getName(), user.getEmail());
//	}
	
	public UserDetailsDTO(User user, List<ProfileDetailsDTO> profiles) {
		this(user.getId(), user.getName(), user.getEmail(), profiles);
	}

}
