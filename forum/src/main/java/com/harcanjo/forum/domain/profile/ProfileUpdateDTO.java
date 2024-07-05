package com.harcanjo.forum.domain.profile;

import jakarta.validation.constraints.NotNull;

public record ProfileUpdateDTO(
		@NotNull
		Long id,
		String name
		) {

}
