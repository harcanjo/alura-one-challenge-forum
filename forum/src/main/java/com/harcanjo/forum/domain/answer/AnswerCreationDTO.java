package com.harcanjo.forum.domain.answer;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerCreationDTO(
		@NotBlank
		String message,
		@NotNull
		@JsonAlias("topic_id")Long topicId		
		) {

}
