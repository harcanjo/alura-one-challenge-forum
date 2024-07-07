package com.harcanjo.forum.domain.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.course.CourseRepository;
import com.harcanjo.forum.domain.topic.validations.ValidatorTopicCreation;
import com.harcanjo.forum.domain.user.User;
import com.harcanjo.forum.domain.user.UserRepository;

@Service
public class TopicCreationService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private List<ValidatorTopicCreation> validators;
	
	public void createTopic(TopicCreationDTO data, @AuthenticationPrincipal User loggedUser) {		
		if (!courseRepository.existsByName(data.courseName())) {
			throw new ValidationException("Course name entered does not exist");
		}
		
		validators.forEach(v -> v.validate(data));
					
		var user = userRepository.getReferenceById(loggedUser.getId());
		var course = courseRepository.getReferenceByName(data.courseName());
		var topic = new Topic(data, user, course);
		topicRepository.save(topic);
	}

	public void deleteTopic(Long id, User loggedUser) {
		if(!topicRepository.existsById(id)) {
			throw new ValidationException("Topic id entered does not exist");
	    }		
		
		var topic = topicRepository.getReferenceById(id);
		
		if (topic.getUser().getId() != loggedUser.getId()) {
			throw new ValidationException("This topic was not created by you (logged in user)");
		}
		
		topic.inactivateTopic();
	}
	
}
