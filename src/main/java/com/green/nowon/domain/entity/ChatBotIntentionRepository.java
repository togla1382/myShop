package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatBotIntentionRepository extends JpaRepository<ChatBotIntention, Long>{

	Optional<ChatBotIntention> findByName(String string);
	List<ChatBotIntention> findByParent(ChatBotIntention entity);
	List<ChatBotIntention> findAllByParent_name(String parentName);
	List<ChatBotIntention> findAllByParent_no(Long parentno);
	Optional<ChatBotIntention> findByNameAndParentNull(String noun);

}
