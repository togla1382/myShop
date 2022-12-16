package com.green.nowon.kafka;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@RestController
public class KafkaController {
	
	private final KafkaProducer producter;
	
	@PostMapping("/kafka")
	public String sendMessage(String message) {
		System.out.println(">>>>KafkaController 실행!");
		this.producter.sendMessage(message);
		return "success";
	}
	
}
