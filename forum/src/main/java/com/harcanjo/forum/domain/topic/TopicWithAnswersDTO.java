package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harcanjo.forum.domain.answer.AnswerToTopicDTO;

public record TopicWithAnswersDTO(
		Long id, 
		String title, 
		String message, 
		@JsonProperty("creation") LocalDateTime createdAt, 
		String user, 
		TopicStatus status,
		List<AnswerToTopicDTO> answers
		) {
	
	public TopicWithAnswersDTO(Topic topic, List<AnswerToTopicDTO> answerDTO) {
		this(
				topic.getId(), 
				topic.getTitle(),
				topic.getMessage(),
				topic.getCreatedAt(), 
				topic.getUser().getName(), 
				topic.getStatus(),
				answerDTO
			);
	}
}
