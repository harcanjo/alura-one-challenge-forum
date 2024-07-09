package com.harcanjo.forum.domain.answer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.harcanjo.forum.domain.user.User;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	Page<Answer> findAllByActiveTrue(Pageable page);

	boolean existsByTopicIdAndMessage(Long topicId, String answerMessage);

	List<Answer> findByUser(User user);

}
