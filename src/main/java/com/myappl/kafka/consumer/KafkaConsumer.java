package com.myappl.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	
	@KafkaListener(topics= {"accountTopic"})
	public void consumeMessages(String value) {
		logger.info("Consuming messages using spring layer: "+value);
		
	}
}
