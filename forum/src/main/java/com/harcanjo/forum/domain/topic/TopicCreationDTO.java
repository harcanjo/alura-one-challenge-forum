package com.harcanjo.forum.domain.topic;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreationDTO(
		@NotNull
		@JsonProperty("course_name")String courseName,
		@NotBlank
		String title,
		@NotBlank
		String message
		) {

}
