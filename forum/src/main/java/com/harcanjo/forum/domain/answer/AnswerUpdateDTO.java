package com.harcanjo.forum.domain.answer;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerUpdateDTO(@NotBlank
		String message,
		@NotNull
		@JsonAlias("topic_id")Long topicID,
		@JsonProperty("solution") Boolean topicSolution
		) {

}
