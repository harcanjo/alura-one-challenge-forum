package com.harcanjo.forum.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegisterDTO(
		@NotBlank
		String name,
		@NotNull
		CourseCategory category
		) {

}
