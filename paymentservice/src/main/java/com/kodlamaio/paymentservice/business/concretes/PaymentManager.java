package com.kodlamaio.paymentservice.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.PaymentCreatedEvent;
import com.kodlamaio.common.rentalPayment.PayMoneyRequest;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;
import com.kodlamaio.paymentservice.entities.Payment;
import com.kodlamaio.paymentservice.kafka.PaymentProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;
	private PaymentProducer paymentProducer;

	@Override
	public CreatePaymentResponse add(PayMoneyRequest createPaymentRequest) {
		checkBalanceEnough(createPaymentRequest.getBalance(), createPaymentRequest.getTotalPrice());
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		payment.setBalance(createPaymentRequest.getBalance());
		payment.setTotalPrice(createPaymentRequest.getTotalPrice());

		Payment result = paymentRepository.save(payment);

//		senkron olması gerektiği için
//		
		PaymentCreatedEvent paymentCreatedEvent = new PaymentCreatedEvent();
		paymentCreatedEvent.setRentalId(createPaymentRequest.getRentalId());
		paymentCreatedEvent.setMessage("Payment carried out");
		paymentProducer.sendMessage(paymentCreatedEvent);

		CreatePaymentResponse createPaymentResponse = modelMapperService.forResponse().map(result,
				CreatePaymentResponse.class);
		return createPaymentResponse;
	}

	private void checkBalanceEnough(double balance, double totalPrice) {
		if (totalPrice > balance) {
			throw new BusinessException("BALANCE.IS.NOT.ENOUGH");
		}
	}

}
