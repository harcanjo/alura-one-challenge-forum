package com.harcanjo.forum.course;

import jakarta.validation.constraints.NotNull;

public record CourseUpdateDTO(
		@NotNull
		Long id,
		String name,
		CourseCategory category
		) {

}
