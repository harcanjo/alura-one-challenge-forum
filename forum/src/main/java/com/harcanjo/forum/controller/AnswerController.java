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
import org.springframework.web.util.UriComponentsBuilder;

import com.harcanjo.forum.domain.answer.AnswerCreationDTO;
import com.harcanjo.forum.domain.answer.AnswerDetailsDTO;
import com.harcanjo.forum.domain.answer.AnswerListDTO;
import com.harcanjo.forum.domain.answer.AnswerRepository;
import com.harcanjo.forum.domain.answer.AnswerService;
import com.harcanjo.forum.domain.answer.AnswerUpdateDTO;
import com.harcanjo.forum.domain.user.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/answers")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<AnswerDetailsDTO> addAnswer(@RequestBody @Valid AnswerCreationDTO data, @AuthenticationPrincipal User loggedUser, UriComponentsBuilder uriBuilder) {
		var dto = answerService.createAnswer(data, loggedUser);		
		
		var uri = uriBuilder.path("/answers/{id}").buildAndExpand(dto.id()).toUri();
		
		//return ResponseEntity.ok(dto);
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteAnswer(@PathVariable Long id, @AuthenticationPrincipal User loggedUser) {
		answerService.deleteAnswer(id, loggedUser);
		return ResponseEntity.noContent().build();
	}
	
	// TODO: @PageableDefault
	@GetMapping
	public ResponseEntity<Page<AnswerListDTO>> showAnswerList(Pageable page){
		var pageList =  repository.findAllByActiveTrue(page).map(AnswerListDTO::new);
		return ResponseEntity.ok(pageList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnswerDetailsDTO> showAnswer(@PathVariable Long id) {
		var dto = answerService.getAnswerById(id);		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AnswerDetailsDTO> updateAnswer(@PathVariable Long id, @RequestBody @Valid AnswerUpdateDTO data, @AuthenticationPrincipal User loggedUser) {
		var dto = answerService.updateAnswer(id, data, loggedUser);		
		return ResponseEntity.ok(dto);
	}
	
}
