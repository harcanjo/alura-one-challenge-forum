package com.harcanjo.forum.domain.answer;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerListDTO(
		Long id, 
		String message, 
		String topic,
		@JsonProperty("creation") LocalDateTime createdAt, 
		String user, 
		@JsonProperty("solution") Boolean topicSolution) {
	
	public AnswerListDTO(Answer answer) {
		this(
				answer.getId(), 
				answer.getMessage(),
				answer.getTopic().getMessage(),
				answer.getCreatedAt(), 
				answer.getUser().getName(), 
				answer.getTopicSolution()
			);
	}

}
