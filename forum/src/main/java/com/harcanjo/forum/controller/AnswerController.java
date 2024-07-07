package com.harcanjo.forum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.domain.answer.AnswerCreationDTO;
import com.harcanjo.forum.domain.answer.AnswerDetailsDTO;
import com.harcanjo.forum.domain.user.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@PostMapping
	@Transactional
	public ResponseEntity<AnswerDetailsDTO> addAnswer(@RequestBody @Valid AnswerCreationDTO data, @AuthenticationPrincipal User loggedUser) {
		System.out.println(data);
		return ResponseEntity.ok(new AnswerDetailsDTO(null, null, null, null, null, null));
	}
}
