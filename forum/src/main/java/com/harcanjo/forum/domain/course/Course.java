package com.harcanjo.forum.domain.course;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="courses")
@Entity(name="Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@Enumerated(EnumType.STRING)
	private CourseCategory category;
	
	private Boolean active;
	
	public Course(CourseRegisterDTO data) {
		this.name = data.name();
		this.category = data.category();
		this.active = true;
	}

	public void updateCourseInformations(@Valid CourseUpdateDTO data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		
		if (data.category() != null) {
			this.category = data.category();
		}
		
	}

	public void inactivateCourse() {
		this.active = false;
	}
	
}
