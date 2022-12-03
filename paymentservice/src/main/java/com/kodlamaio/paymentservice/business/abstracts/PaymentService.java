package com.kodlamaio.paymentservice.business.abstracts;

import com.kodlamaio.common.event.RentalPaymentCreatedEvent;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;

public interface PaymentService {

	CreatePaymentResponse add(RentalPaymentCreatedEvent createPaymentRequest);
}
