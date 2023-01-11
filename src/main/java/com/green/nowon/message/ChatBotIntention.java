package com.green.nowon.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class ChatBotIntention {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	private String name;
	private String answer;
	
	@JoinColumn
	@ManyToOne
	private ChatBotIntention parent;

}
