package com.harcanjo.forum.profile;

public record ProfileDetailsDTO(Long id, String name) {
	
	public ProfileDetailsDTO(Profile profile) {
		this(profile.getId(), profile.getName());
	}

}
