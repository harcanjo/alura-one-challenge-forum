package com.harcanjo.forum.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDetailsDTO(Long id, String title, String message, LocalDateTime createdAt, String user, TopicStatus status, List<String> answers) {

}
