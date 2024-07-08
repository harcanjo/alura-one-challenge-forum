package com.harcanjo.forum.domain.topic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.answer.Answer;
import com.harcanjo.forum.domain.answer.AnswerToTopicDTO;
import com.harcanjo.forum.domain.course.CourseRepository;
import com.harcanjo.forum.domain.topic.validations.create.ValidatorTopicCreation;
import com.harcanjo.forum.domain.user.User;
import com.harcanjo.forum.domain.user.UserRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private List<ValidatorTopicCreation> creationValidators;
	
	public TopicDetailsDTO createTopic(TopicCreationDTO data, @AuthenticationPrincipal User loggedUser) {		
		if (!courseRepository.existsByName(data.courseName())) {
			throw new ValidationException("Course name entered does not exist");
		}
		
		creationValidators.forEach(v -> v.validate(data));
					
		var user = userRepository.getReferenceById(loggedUser.getId());
		var course = courseRepository.getReferenceByName(data.courseName());
		var topic = new Topic(data, user, course);
		topicRepository.save(topic);
		
		return new TopicDetailsDTO(topic);
	}
	
	public TopicDetailsDTO updateTopic(Long id, TopicUpdateDTO data, @AuthenticationPrincipal User loggedUser) {
		if(data.courseName() != null) {
			if (!courseRepository.existsByName(data.courseName())) {
				throw new ValidationException("Course name entered does not exist");
			}
		}
		
		if (topicRepository.getReferenceById(id).getUser() != userRepository.getReferenceById(loggedUser.getId())) {
			throw new ValidationException("Topic was created by another user");
		}
		
		var course = courseRepository.getReferenceByName(data.courseName());
		var topic = topicRepository.getReferenceById(id);
		
		topic.updateTopicInformations(data, course);
		
		return new TopicDetailsDTO(topic);
	}

	public void deleteTopic(Long id, User loggedUser) {
		if(!topicRepository.existsById(id)) {
			throw new ValidationException("Topic id entered does not exist");
	    }		
		
		var topic = topicRepository.getReferenceById(id);
		
		if (topic.getUser().getId() != loggedUser.getId()) {
			throw new ValidationException("This topic was not created by you (logged in user)");
		}
		
		// Logical Deletion
		// topic.inactivateTopic();
		
		topicRepository.deleteById(id);
	}	
	
	// TODO: Only show topic
//	public TopicDetailsDTO showTopicByID(Long id) {
//		if(!topicRepository.existsById(id)) {
//			throw new ValidationException("Topic id entered does not exist");
//	    }		
//		
//		var topic = topicRepository.getReferenceById(id);
//		
//		return new TopicDetailsDTO(topic);
//	}
	
	public TopicWithAnswersDTO getTopicById(Long id) {
		Topic topic = topicRepository.findById(id).orElseThrow(() -> new ValidationException("Topic id entered does not exist"));
		
		List<AnswerToTopicDTO> answerDTO = topic.getAnswers().stream()
				.map(this::mapAnswerToTopicDTO)
				.collect(Collectors.toList());
		
		return new TopicWithAnswersDTO(topic, answerDTO);
	}
	
	private AnswerToTopicDTO mapAnswerToTopicDTO(Answer answer) {
		return new AnswerToTopicDTO(answer);
	}
	
}
