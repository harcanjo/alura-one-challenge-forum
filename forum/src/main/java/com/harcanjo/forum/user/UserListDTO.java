package com.harcanjo.forum.user;

public record UserListDTO(
		Long id,
		String name
		) {

	public UserListDTO(User user) {
		this(user.getId(), user.getName());
	}
	
}
