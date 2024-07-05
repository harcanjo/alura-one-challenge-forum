package com.harcanjo.forum.users;

import com.harcanjo.forum.user.User;

public record UserDetailsDTO(Long id, String name, String email) {
	
	public UserDetailsDTO(User user) {
		this(user.getId(), user.getName(), user.getEmail());
	}

}
