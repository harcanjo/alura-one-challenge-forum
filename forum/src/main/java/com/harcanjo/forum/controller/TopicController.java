package com.harcanjo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;
import com.harcanjo.forum.domain.topic.TopicDetailsDTO;
import com.harcanjo.forum.domain.topic.TopicService;
import com.harcanjo.forum.domain.user.User;
import com.harcanjo.forum.domain.user.UserListDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid TopicCreationDTO data, @AuthenticationPrincipal User loggedUser) {
		topicService.createTopic(data, loggedUser);		
		return ResponseEntity.ok(new TopicDetailsDTO(null, null, null, null, null, null, null));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id, @AuthenticationPrincipal User loggedUser) {
		topicService.deleteTopic(id, loggedUser);
		return ResponseEntity.noContent().build();
	}
}
