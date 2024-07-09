package com.harcanjo.forum.domain.user;

public record UserListDTO(
		Long id,
		String name,
		String email
		) {

	public UserListDTO(User user) {
		this(user.getId(), 
			 user.getName(), 
			 user.getEmail()
			);
	}
	
}
