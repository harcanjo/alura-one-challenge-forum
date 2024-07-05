package com.harcanjo.forum.domain.user;

public record UserListDTO(
		Long id,
		String name
		) {

	public UserListDTO(User user) {
		this(user.getId(), user.getName());
	}
	
}
