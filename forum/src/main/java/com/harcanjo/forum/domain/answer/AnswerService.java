package com.harcanjo.forum.domain.answer;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.answer.validations.create.ValidatorAnswerCreation;
import com.harcanjo.forum.domain.topic.TopicRepository;
import com.harcanjo.forum.domain.topic.TopicStatus;
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
	
	@Autowired
	private List<ValidatorAnswerCreation> creationValidators;

	public AnswerDetailsDTO createAnswer(@Valid AnswerCreationDTO data, @AuthenticationPrincipal User loggedUser) {		
		if (!topicRepository.existsById(data.topicId())) {
			throw new ValidationException("Topic id entered does not exist");
		}
		
		creationValidators.forEach(v -> v.validate(data));
					
		var user = userRepository.getReferenceById(loggedUser.getId());
		var topic = topicRepository.getReferenceById(data.topicId());
		var answer = new Answer(data, user, topic);
		answerRepository.save(answer);
		
		topic.getAnswers().add(answer);
		topic.setStatus(TopicStatus.ANSWERED);
		
		return new AnswerDetailsDTO(answer);
	}

	public void deleteAnswer(Long id, User loggedUser) {
		if(!answerRepository.existsById(id)) {
			throw new ValidationException("Answer id entered does not exist");
	    }		
		
		var answer = answerRepository.getReferenceById(id);
		
		if (answer.getUser().getId() != loggedUser.getId()) {
			throw new ValidationException("This answer was not created by you (logged in user)");
		}
		
		// Logical Deletion
		// answer.inactivateAnswer();
		
		answerRepository.deleteById(id);		
	}

	public AnswerDetailsDTO getAnswerById(Long id) {
		return new AnswerDetailsDTO(answerRepository.getReferenceById(id));
	}

	public AnswerDetailsDTO updateAnswer(Long id, @Valid AnswerUpdateDTO data, User loggedUser) {
		if (!answerRepository.existsById(id)) {
			throw new ValidationException("Answer id entered does not exist");
		}
		
		if (answerRepository.getReferenceById(id).getUser() != userRepository.getReferenceById(loggedUser.getId())) {
			throw new ValidationException("Answer was created by another user");
		}
		
		var answer = answerRepository.getReferenceById(id);
		var topic = topicRepository.getReferenceById(data.topicID());
		
		answer.updateAnswerInformations(data, topic);
		
		return new AnswerDetailsDTO(answer);
	}
}
