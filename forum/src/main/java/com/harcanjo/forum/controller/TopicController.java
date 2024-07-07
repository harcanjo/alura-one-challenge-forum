package com.harcanjo.forum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;
import com.harcanjo.forum.domain.topic.TopicDetailsDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicDetailsDTO>createTopic(@RequestBody @Valid TopicCreationDTO data) {
		System.out.println(data);
		return ResponseEntity.ok(new TopicDetailsDTO(null, null, null, null, null, null, null));
	}

}
