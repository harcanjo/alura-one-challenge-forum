package com.harcanjo.forum.profile;

import jakarta.validation.constraints.NotBlank;

public record ProfileRegisterDTO(
		@NotBlank
		String name
		) {

}
