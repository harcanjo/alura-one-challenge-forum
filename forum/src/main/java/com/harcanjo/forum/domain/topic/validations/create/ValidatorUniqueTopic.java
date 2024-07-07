package com.harcanjo.forum.domain.topic.validations.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.topic.TopicCreationDTO;
import com.harcanjo.forum.domain.topic.TopicRepository;

@Component
public class ValidatorUniqueTopic implements ValidatorTopicCreation {
	
	@Autowired
	private TopicRepository repository;
	
	public void validate(TopicCreationDTO data) {
		var topicTitle = data.title();
		var topicMessage = data.message();
		
		var topicAlreadyExists = repository.existsByTitleAndMessage(topicTitle, topicMessage);
		
		if(topicAlreadyExists) {
			throw new ValidationException("This topic already exists, avoid duplicates");
		}
	}

}
