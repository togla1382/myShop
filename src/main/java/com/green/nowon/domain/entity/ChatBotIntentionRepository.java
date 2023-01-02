package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatBotIntentionRepository extends JpaRepository<ChatBotIntention, Long>{

	Optional<ChatBotIntention> findByName(String string);
	List<ChatBotIntention> findByParent(ChatBotIntention entity);
	List<ChatBotIntention> findByParent_no(long parentNo);
	Optional<ChatBotIntention> findByNameAndParentNull(String noun);

}
