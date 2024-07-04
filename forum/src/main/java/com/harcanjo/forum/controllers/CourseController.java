package com.harcanjo.forum.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.course.CourseRegisterDTO;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@PostMapping
	public void register(@RequestBody CourseRegisterDTO data) {
		System.out.println("Data received: " + data);
	}
}
