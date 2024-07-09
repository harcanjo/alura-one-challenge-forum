package com.harcanjo.forum.domain.topic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.harcanjo.forum.domain.user.User;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	Page<Topic> findAllByActiveTrue(Pageable page);

	boolean existsByTitleAndMessage(String topicTitle, String topicMessage);
	
	List<Topic> findByUser(User user);
}
