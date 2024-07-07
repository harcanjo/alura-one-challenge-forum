package com.harcanjo.forum.domain.topic.validations.create;

import com.harcanjo.forum.domain.topic.TopicCreationDTO;

public interface ValidatorTopicCreation {

	void validate(TopicCreationDTO data);
}
