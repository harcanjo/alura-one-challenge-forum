package com.harcanjo.forum.domain.profile;

public record ProfileDetailsDTO(Long id, String name) {
	
	public ProfileDetailsDTO(Profile profile) {
		this(profile.getId(), profile.getName());
	}

}
