package com.harcanjo.forum.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerCreationDTO(
		@NotBlank
		String message,
		@NotNull
		Long topicID		
		) {

}
