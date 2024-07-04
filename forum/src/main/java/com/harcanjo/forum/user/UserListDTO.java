package com.harcanjo.forum.user;

public record UserListDTO(
		String name
		) {

	public UserListDTO(User user) {
		this(user.getName());
	}
	
}
