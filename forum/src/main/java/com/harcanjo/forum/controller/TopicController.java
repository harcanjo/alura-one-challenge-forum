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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;
import com.harcanjo.forum.domain.topic.TopicDetailsDTO;
import com.harcanjo.forum.domain.topic.TopicListDTO;
import com.harcanjo.forum.domain.topic.TopicRepository;
import com.harcanjo.forum.domain.topic.TopicService;
import com.harcanjo.forum.domain.topic.TopicUpdateDTO;
import com.harcanjo.forum.domain.user.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid TopicCreationDTO data, @AuthenticationPrincipal User loggedUser) {
		var dto = topicService.createTopic(data, loggedUser);		
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id, @AuthenticationPrincipal User loggedUser) {
		topicService.deleteTopic(id, loggedUser);
		return ResponseEntity.noContent().build();
	}
	
	// TODO: 10 first results, ordered by created date in order ASC
	// TODO: list by name and year
	// @PageableDefault
	@GetMapping
	public ResponseEntity<Page<TopicListDTO>> showTopicList(Pageable page){
		var pageList =  repository.findAllByActiveTrue(page).map(TopicListDTO::new);
		return ResponseEntity.ok(pageList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailsDTO> showTopic(@PathVariable Long id) {
		var dto = topicService.showTopicByID(id);		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDetailsDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO data, @AuthenticationPrincipal User loggedUser) {
		var dto = topicService.updateTopic(id, data, loggedUser);		
		return ResponseEntity.ok(dto);
	}
}
