package com.harcanjo.forum.profile;

public record ProfileListDTO(
		String name
		) {
	
	public ProfileListDTO(Profile profile) {
		this(profile.getName());
	}
}
