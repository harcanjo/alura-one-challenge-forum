package com.harcanjo.forum.domain.answer;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerToTopicDTO(
		Long id, 
		String message, 
		@JsonProperty("creation") LocalDateTime createdAt, 
		String user, 
		@JsonProperty("solution") Boolean topicSolution) {

	public AnswerToTopicDTO(Answer answer) {
		this(
				answer.getId(), 
				answer.getMessage(),
				answer.getCreatedAt(), 
				answer.getUser().getName(), 
				answer.getTopicSolution()
			);
	}
}