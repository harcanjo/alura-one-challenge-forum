package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TopicListDTO(
		Long id,
		String title,
		String message,
		@JsonProperty("created_at")LocalDateTime createdAt,
		TopicStatus status,
		@JsonProperty("user_name")String userName,
		@JsonProperty("course_name")String courseName
		) {

	public TopicListDTO(Topic topic) {
		this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt(), topic.getStatus(), topic.getUser().getName(), topic.getCourse().getName());
	}
}
