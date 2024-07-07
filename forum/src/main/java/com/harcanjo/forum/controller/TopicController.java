package com.harcanjo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;
import com.harcanjo.forum.domain.topic.TopicDetailsDTO;
import com.harcanjo.forum.domain.topic.TopicService;
import com.harcanjo.forum.domain.user.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicDetailsDTO>createTopic(@RequestBody @Valid TopicCreationDTO data, @AuthenticationPrincipal User loggedUser) {
		topicService.createTopic(data, loggedUser);		
		return ResponseEntity.ok(new TopicDetailsDTO(null, null, null, null, null, null, null));
	}

}
