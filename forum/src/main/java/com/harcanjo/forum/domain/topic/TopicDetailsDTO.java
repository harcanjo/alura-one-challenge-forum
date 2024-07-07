package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDetailsDTO(Long id, String title, String message, LocalDateTime createdAt, String user, TopicStatus status, List<String> answers) {

	public TopicDetailsDTO(Topic topic) {
		this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt(), topic.getUser().getName(), topic.getStatus(), topic.getAnswers());
	}

}
