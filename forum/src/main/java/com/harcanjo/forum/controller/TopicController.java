package com.harcanjo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;
import com.harcanjo.forum.domain.topic.TopicDetailsDTO;
import com.harcanjo.forum.domain.topic.TopicListDTO;
import com.harcanjo.forum.domain.topic.TopicRepository;
import com.harcanjo.forum.domain.topic.TopicService;
import com.harcanjo.forum.domain.topic.TopicUpdateDTO;
import com.harcanjo.forum.domain.topic.TopicWithAnswersDTO;
import com.harcanjo.forum.domain.user.User;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid TopicCreationDTO data, @AuthenticationPrincipal User loggedUser, UriComponentsBuilder uriBuilder) {
		var dto = topicService.createTopic(data, loggedUser);
		
		var uri = uriBuilder.path("/topics/{id}").buildAndExpand(dto.id()).toUri();
		
		//return ResponseEntity.ok(dto);
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id, @AuthenticationPrincipal User loggedUser) {
		topicService.deleteTopic(id, loggedUser);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<TopicListDTO>> showTopicList(Pageable page){
		var pageList =  repository.findAllByActiveTrue(page).map(TopicListDTO::new);
		return ResponseEntity.ok(pageList);
	}
	
	@GetMapping("/first-topics")
	public ResponseEntity<Page<TopicListDTO>> showFirstTopicList(@PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable page){
		var pageList =  repository.findAllByActiveTrue(page).map(TopicListDTO::new);
		return ResponseEntity.ok(pageList);
	}

	// TODO: Only show topic
//	@GetMapping("/{id}")
//	public ResponseEntity<TopicDetailsDTO> showTopic(@PathVariable Long id) {
//		var dto = topicService.showTopicByID(id);		
//		return ResponseEntity.ok(dto);
//	}
	
	// Show topic with answer list
	@GetMapping("/{id}")
	public ResponseEntity<TopicWithAnswersDTO> showTopic(@PathVariable Long id) {
		var dto = topicService.getTopicById(id);		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<TopicDetailsDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO data, @AuthenticationPrincipal User loggedUser) {
		var dto = topicService.updateTopic(id, data, loggedUser);		
		return ResponseEntity.ok(dto);
	}
}
