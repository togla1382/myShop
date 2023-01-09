package com.green.nowon.kafka;

import java.io.IOException;

//@Service
public class KafkaConsumer {
	
	
	//@KafkaListener(topics = "nowon", groupId = "testgroup")
	public void consume(String message) throws IOException{
		System.out.printf("Consumed : %s\n",message);
	}

}
