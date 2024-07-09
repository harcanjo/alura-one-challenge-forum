package com.harcanjo.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/hello")
@SecurityRequirement(name = "bearer-key")
public class HelloController {

	@GetMapping
	public String RegisterHello() {
		return "Hello, World!";
	}
}
