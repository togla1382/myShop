package com.green.nowon.kafka;

import org.springframework.beans.factory.annotation.Value;

//@RequiredArgsConstructor
//@Service
public class KafkaProducer {
	
	//private final KafkaTemplate<String , String> kafkaTemplate;
	
	@Value(value="${message.topic.name}")
	private String TOPIC;
	
	
	public void sendMessage(String message) {
		System.out.printf("Produce message : %s\n", message);
		//this.kafkaTemplate.send(TOPIC, message);
	}
	
	

}
