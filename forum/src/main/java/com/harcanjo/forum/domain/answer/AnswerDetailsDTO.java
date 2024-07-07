package com.harcanjo.forum.domain.answer;

import java.time.LocalDateTime;

public record AnswerDetailsDTO(Long id, String message, String topic, LocalDateTime createdAt, String user, Boolean topicSolution) {

	public AnswerDetailsDTO(Answer answer) {
		this(answer.getId(), answer.getMessage(), answer.getTopic().getMessage(), answer.getCreatedAt(), answer.getUser().getName(), answer.getTopicSolution());
	}
}
