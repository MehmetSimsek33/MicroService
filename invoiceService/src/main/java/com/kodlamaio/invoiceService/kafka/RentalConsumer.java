package com.kodlamaio.invoiceService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.RentalCreateInvoice;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RentalConsumer {

	private InvoiceService invoiceService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "invoiceCreate")
	public void consume(RentalCreateInvoice event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		invoiceService.add(event);
	}

}