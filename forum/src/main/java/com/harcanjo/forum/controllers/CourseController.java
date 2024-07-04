package com.harcanjo.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.course.Course;
import com.harcanjo.forum.course.CourseRegisterDTO;
import com.harcanjo.forum.course.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository repository;
	
	@PostMapping
	public void register(@RequestBody CourseRegisterDTO data) {
		repository.save(new Course(data));
	}
}
