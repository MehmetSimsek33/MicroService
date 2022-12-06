package com.kodlamaio.rentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.RentalCreateInvoice;
import com.kodlamaio.common.event.RentalCreatedEvent;
import com.kodlamaio.common.event.RentalPaymentCreatedEvent;
import com.kodlamaio.common.event.RentalUpdatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class);

	private NewTopic topic;

	private KafkaTemplate<String, RentalCreatedEvent> kafkaTemplateCreated;

	private KafkaTemplate<String, RentalUpdatedEvent> kafkaTemplateUpdated;

//	private KafkaTemplate<String, RentalPaymentCreatedEvent> kafkaTemplatePaymentCreated;
//
//	private KafkaTemplate<String, RentalCreateInvoice> kafkaTemplateInvoice;

	public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
		LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));

		Message<RentalCreatedEvent> message = MessageBuilder.withPayload(rentalCreatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();

		kafkaTemplateCreated.send(message);
	}

	public void sendMessage(RentalUpdatedEvent rentalUpdatedEvent) {
		LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));

		Message<RentalUpdatedEvent> message = MessageBuilder.withPayload(rentalUpdatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();

		kafkaTemplateUpdated.send(message);
	}

//	public void sendMessage(RentalPaymentCreatedEvent rentalPaymentCreatedEvent) {
//		LOGGER.info(String.format("Kafka updated event => %s", rentalPaymentCreatedEvent.toString()));
//
//		Message<RentalPaymentCreatedEvent> message = MessageBuilder.withPayload(rentalPaymentCreatedEvent)
//				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
//
//		kafkaTemplatePaymentCreated.send(message);
//	}

//	public void sendMessage(RentalCreateInvoice rentalCreateInvoice) {
//		LOGGER.info(String.format("Kafka invoice event => %s", rentalCreateInvoice.toString()));
//
//		Message<RentalCreateInvoice> message = MessageBuilder.withPayload(rentalCreateInvoice)
//				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
//
//		kafkaTemplateInvoice.send(message);
//	}

}