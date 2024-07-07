package com.harcanjo.forum.domain.topic.validations;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;

public interface ValidatorTopicCreation {

	void validate(TopicCreationDTO data);
}
