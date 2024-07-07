package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.harcanjo.forum.domain.course.Course;
import com.harcanjo.forum.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String message;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	private TopicStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
	private Boolean active;
	
	// TODO: create answers type
	private List<String> answers;

	public Topic(TopicCreationDTO data, User user, Course course) {
		this.title = data.title();
		this.message = data.message();
		this.createdAt = LocalDateTime.now();
		this.status = TopicStatus.NOT_ANSWERED;
		this.user = user;
		this.course = course;
		this.active = true;
		this.answers = new ArrayList<>();
	}

}
