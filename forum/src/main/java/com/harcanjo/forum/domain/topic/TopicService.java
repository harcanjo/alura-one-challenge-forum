package com.harcanjo.forum.domain.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.harcanjo.forum.domain.ValidationException;
import com.harcanjo.forum.domain.course.CourseRepository;
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
	
	public void createTopic(TopicCreationDTO data, @AuthenticationPrincipal User loggedUser) {		
		if (!courseRepository.existsByName(data.courseName())) {
			throw new ValidationException("Course name entered does not exist");
		}
					
		var user = userRepository.findById(loggedUser.getId()).get();
		var course = courseRepository.findByName(data.courseName());
		var topic = new Topic(data, user, course);
		topicRepository.save(topic);
	}
	
}
