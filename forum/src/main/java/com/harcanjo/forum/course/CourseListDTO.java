package com.harcanjo.forum.course;

public record CourseListDTO(
		Long id,
		String name,
		CourseCategory category
		) {

	public CourseListDTO(Course course) {
		this(course.getId() ,course.getName(), course.getCategory());
	}
}
