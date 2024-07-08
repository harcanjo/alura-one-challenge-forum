package com.harcanjo.forum.domain.answer;

import java.time.LocalDateTime;

import com.harcanjo.forum.domain.topic.Topic;
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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "answers")
@Entity(name = "Answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topic;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "topic_solution")
	private Boolean topicSolution;
	
	private Boolean active;	

	public Answer(@Valid AnswerCreationDTO data, User user, Topic topic) {
		this.message = data.message();
		this.topic = topic;
		this.createdAt = LocalDateTime.now();
		this.user = user;
		this.topicSolution = false;
		this.active = true;
	}

}
