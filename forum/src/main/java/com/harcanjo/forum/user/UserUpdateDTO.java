package com.harcanjo.forum.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
		@NotNull
		Long id,
		String name,
		String password
		) {
	
	

}
