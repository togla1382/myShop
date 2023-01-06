package com.green.nowon.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	
	@KafkaListener(topics = "nowon", groupId = "testgroup")
	public void consume(String message) throws IOException{
		System.out.printf("Consumed : %s\n",message);
	}

}
