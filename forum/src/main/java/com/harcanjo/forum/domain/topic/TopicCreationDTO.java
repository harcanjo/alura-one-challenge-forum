package com.harcanjo.forum.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreationDTO(
		@NotNull
		String courseName,
		@NotBlank
		String title,
		@NotBlank
		String message
		) {

}
