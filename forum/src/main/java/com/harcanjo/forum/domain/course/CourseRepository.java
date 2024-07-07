package com.harcanjo.forum.domain.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Page<Course> findAllByActiveTrue(Pageable page);

	Course findByName(String courseName);

	boolean existsByName(String courseName);

	Course getReferenceByName(String courseName);

}
