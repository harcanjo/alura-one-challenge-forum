package com.harcanjo.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harcanjo.forum.course.Course;
import com.harcanjo.forum.course.CourseListDTO;
import com.harcanjo.forum.course.CourseRegisterDTO;
import com.harcanjo.forum.course.CourseRepository;
import com.harcanjo.forum.course.CourseUpdateDTO;

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
	public Page<CourseListDTO> showCourseList(Pageable page){
		return repository.findAll(page).map(CourseListDTO::new);
	}
	
	@PutMapping
	@Transactional
	public void updateCourse(@RequestBody @Valid CourseUpdateDTO data) {
		var course = repository.getReferenceById(data.id());
		course.updateCourseInformations(data);
	}
}
