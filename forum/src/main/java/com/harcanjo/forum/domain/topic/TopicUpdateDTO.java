package com.harcanjo.forum.domain.topic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
		@NotNull
		Long id,
		String courseName,
		String title,
		String message
		) {

}
