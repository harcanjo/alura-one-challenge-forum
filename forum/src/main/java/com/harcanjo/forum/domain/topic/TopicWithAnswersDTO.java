package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harcanjo.forum.domain.answer.AnswerToTopicDTO;

public record TopicWithAnswersDTO(
		Long id, 
		String title, 
		String message, 
		@JsonProperty("created_at") LocalDateTime createdAt, 
		TopicStatus status,
		@JsonProperty("user_name") String userName,
		@JsonProperty("course_name") String courseName,
		List<AnswerToTopicDTO> answers
		) {
	
	public TopicWithAnswersDTO(Topic topic, List<AnswerToTopicDTO> answerDTO) {
		this(
				topic.getId(), 
				topic.getTitle(),
				topic.getMessage(),
				topic.getCreatedAt(), 
				topic.getStatus(),
				topic.getUser().getName(),
				topic.getCourse().getName(),
				answerDTO
			);
	}
}
