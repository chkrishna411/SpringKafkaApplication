package com.myappl.kafka.controller;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myappl.kafka.domain.Message;

@RestController
public class MessageController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@PostMapping("/send/message")
	public void sendMessage(@RequestBody  Message message) throws InterruptedException, ExecutionException {
		logger.info("sending message to kafka: "+message.getValue());
		
		ListenableFuture<SendResult<String, String>> response = 
				this.kafkaTemplate.send("accountTopic", message.getValue());
		
		
		SendResult<String, String> sendResult = response.get();
		
		RecordMetadata metadata = sendResult.getRecordMetadata();
		logger.info("Message sent to Topic:"+metadata.topic() + " offset:"
				+metadata.offset() + " partition:"+metadata.partition());
	}
}
