package com.harcanjo.forum.domain.answer.validations.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.answer.AnswerCreationDTO;
import com.harcanjo.forum.domain.answer.AnswerRepository;

@Component
public class ValidatorUniqueAnswer implements ValidatorAnswerCreation {
	
	@Autowired
	private AnswerRepository repository;
	
	public void validate(AnswerCreationDTO data) {
		var topicId = data.topicId();
		var answerMessage = data.message();
		
		var answerAlreadyExists = repository.existsByTopicIdAndMessage(topicId, answerMessage);
		
		if(answerAlreadyExists) {
			throw new ValidationException("This topic already have this answer, avoid duplicates");
		}
	}

}
