package com.myappl.kafka.config;

import java.util.HashMap;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.core.task.AsyncListenableTaskExecutor;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import org.springframework.kafka.core.ConsumerFactory;

import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration

public class KafkaConsumerConfig {

	@Bean

	public ConsumerFactory<String, String> consumerConfig() {

		Map<String, Object> configProps = new HashMap<>();

		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "accountGroup");

		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

		return new DefaultKafkaConsumerFactory<>(configProps);

	}

	@Bean

	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();

		factory.setConsumerFactory(this.consumerConfig());

		factory.getContainerProperties().setConsumerTaskExecutor(this.getAsyncExecutor());

		return factory;

	}

	private AsyncListenableTaskExecutor getAsyncExecutor() {

		ThreadPoolTaskExecutor executore = new ThreadPoolTaskExecutor();

		executore.setCorePoolSize(10);
		executore.initialize();
		return executore;

	}

}