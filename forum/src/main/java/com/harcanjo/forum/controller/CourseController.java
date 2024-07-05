package com.harcanjo.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.harcanjo.forum.domain.course.Course;
import com.harcanjo.forum.domain.course.CourseDetailsDTO;
import com.harcanjo.forum.domain.course.CourseListDTO;
import com.harcanjo.forum.domain.course.CourseRegisterDTO;
import com.harcanjo.forum.domain.course.CourseRepository;
import com.harcanjo.forum.domain.course.CourseUpdateDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CourseDetailsDTO> addCourse(@RequestBody @Valid CourseRegisterDTO data, UriComponentsBuilder uriBuilder) {
		var course = new Course(data);
		repository.save(course);
		
		var uri = uriBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
		return ResponseEntity.created(uri).body(new CourseDetailsDTO(course));
	}
	
	@GetMapping
	public ResponseEntity<Page<CourseListDTO>> showCourseList(Pageable page){
		var pageList = repository.findAllByActiveTrue(page).map(CourseListDTO::new);	
		return ResponseEntity.ok(pageList);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<CourseDetailsDTO> updateCourse(@RequestBody @Valid CourseUpdateDTO data) {
		var course = repository.getReferenceById(data.id());
		course.updateCourseInformations(data);
		
		return ResponseEntity.ok(new CourseDetailsDTO(course));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		var course = repository.getReferenceById(id);
		course.inactivateCourse();
		
		return ResponseEntity.noContent().build();
	}
	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void deleteCourse(@PathVariable Long id) {
//		repository.deleteById(id);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseDetailsDTO> showCourse(@PathVariable Long id) {
		var course = repository.getReferenceById(id);		
		return ResponseEntity.ok(new CourseDetailsDTO(course));
	}
}
