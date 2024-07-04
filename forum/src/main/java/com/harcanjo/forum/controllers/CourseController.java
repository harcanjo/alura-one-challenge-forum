package com.harcanjo.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.course.Course;
import com.harcanjo.forum.course.CourseListDTO;
import com.harcanjo.forum.course.CourseRegisterDTO;
import com.harcanjo.forum.course.CourseRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository repository;
	
	@PostMapping
	@Transactional
	public void addCourse(@RequestBody @Valid CourseRegisterDTO data) {
		repository.save(new Course(data));
	}
	
	@GetMapping
	public List<CourseListDTO> showCourseList(){
		return repository.findAll().stream().map(CourseListDTO::new).toList();
	}
}
