package com.harcanjo.forum.domain.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.topic.TopicRepository;
import com.harcanjo.forum.domain.user.User;
import com.harcanjo.forum.domain.user.UserRepository;

import jakarta.validation.Valid;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;

	public AnswerDetailsDTO createAnswer(@Valid AnswerCreationDTO data, @AuthenticationPrincipal User loggedUser) {		
		if (!topicRepository.existsById(data.topicID())) {
			throw new ValidationException("Topic id entered does not exist");
		}
		
		// creationValidators.forEach(v -> v.validate(data));
					
		var user = userRepository.getReferenceById(loggedUser.getId());
		var topic = topicRepository.getReferenceById(data.topicID());
		var answer = new Answer(data, user, topic);
		answerRepository.save(answer);
		
		System.out.println(answer);
		
		return new AnswerDetailsDTO(answer);
	}
}
