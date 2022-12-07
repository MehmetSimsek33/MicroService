package com.kodlamaio.filterService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.filter.brand.BrandUpdatedEvent;
import com.kodlamaio.common.event.filter.car.CarCreatedEvent;
import com.kodlamaio.common.event.filter.car.CarDeletedEvent;
import com.kodlamaio.common.event.filter.car.CarUpdatedEvent;
import com.kodlamaio.common.event.filter.model.ModelUpdatedEvent;
import com.kodlamaio.filterService.business.abstracts.FilterService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryConsumer.class);
	private FilterService filterService;

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "CREATECAR")
	public void consume(@Payload CarCreatedEvent event) {
		LOGGER.info(String.format("Car event received in filter service => %s", event.toString()));
		filterService.addCar(event);

	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "UPDATECAR")
	public void consume(@Payload CarUpdatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		filterService.updateCar(event);
		// save the order event into the database
	}
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "DELETECAR")
	public void consume(@Payload CarDeletedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		filterService.deleteCar(event);
		
	}

	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "UPDATEBRAND")
	public void consume(@Payload  BrandUpdatedEvent event) {
		LOGGER.info(String.format("Brand event received in filter service => %s", event.toString()));
		filterService.updateBrand(event);
		
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "updatemodel")
	public void consume(ModelUpdatedEvent event) {
		LOGGER.info(String.format("Model event received in filter service => %s", event.toString()));
		filterService.updateModel(event);
		
	}
}
