package com.harcanjo.forum.domain.profile;

public record ProfileListDTO(
		Long id,
		String name
		) {
	
	public ProfileListDTO(Profile profile) {
		this(profile.getId(), profile.getName());
	}
}
