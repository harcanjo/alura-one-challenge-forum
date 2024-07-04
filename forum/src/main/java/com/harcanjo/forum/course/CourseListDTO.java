package com.harcanjo.forum.course;

public record CourseListDTO(
		String name,
		CourseCategory category
		) {

	public CourseListDTO(Course course) {
		this(course.getName(), course.getCategory());
	}
}
