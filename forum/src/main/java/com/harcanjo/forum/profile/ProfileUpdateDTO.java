package com.harcanjo.forum.profile;

import jakarta.validation.constraints.NotNull;

public record ProfileUpdateDTO(
		@NotNull
		Long id,
		String name
		) {

}
