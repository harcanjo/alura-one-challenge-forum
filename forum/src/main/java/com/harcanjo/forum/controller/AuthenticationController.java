package com.harcanjo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.domain.user.AuthenticationDTO;
import com.harcanjo.forum.domain.user.User;
import com.harcanjo.forum.infra.security.JWTTokenDTO;
import com.harcanjo.forum.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<JWTTokenDTO> loginAuth(@RequestBody @Valid AuthenticationDTO data) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var authentication = manager.authenticate(authenticationToken);
		
		var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());
		
		return ResponseEntity.ok(new JWTTokenDTO(jwtToken));
	}

}
