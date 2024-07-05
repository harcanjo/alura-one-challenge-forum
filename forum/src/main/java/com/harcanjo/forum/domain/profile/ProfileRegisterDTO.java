package com.harcanjo.forum.domain.profile;

import jakarta.validation.constraints.NotBlank;

public record ProfileRegisterDTO(
		@NotBlank
		String name
		) {

}
