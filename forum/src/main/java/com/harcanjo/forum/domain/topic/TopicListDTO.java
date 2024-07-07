package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;

public record TopicListDTO(
		String title,
		String message,
		LocalDateTime createdAt,
		TopicStatus status,
		String userName,
		String courseName
		) {

	public TopicListDTO(Topic topic) {
		this(topic.getTitle(), topic.getMessage(), topic.getCreatedAt(), topic.getStatus(), topic.getUser().getName(), topic.getCourse().getName());
	}
}
