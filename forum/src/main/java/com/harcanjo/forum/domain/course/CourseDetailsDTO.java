package com.harcanjo.forum.domain.course;

public record CourseDetailsDTO(Long id, String name, CourseCategory category) {

	public CourseDetailsDTO(Course course) {
		this(course.getId(), course.getName(), course.getCategory());
	}
}
