package com.kodlamaio.paymentservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.RentalPaymentCreatedEvent;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RentalConsumer {

	private PaymentService paymentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "paymentCreate")
	public void consume(RentalPaymentCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		paymentService.add(event);
		// save the order event into the database
	}

}