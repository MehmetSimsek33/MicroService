package com.kodlamaio.invoiceService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.PaymentCreatedEvent;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class PaymentConsumer {
	private InvoiceService invoiceService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "createInvoice"
    )
    public void consume(PaymentCreatedEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        invoiceService.add(event);
    }
}   