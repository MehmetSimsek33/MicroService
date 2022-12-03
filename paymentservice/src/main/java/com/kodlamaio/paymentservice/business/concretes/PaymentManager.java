package com.kodlamaio.paymentservice.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.RentalPaymentCreatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;
import com.kodlamaio.paymentservice.entities.Payment;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
	
	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;

	@Override
	public CreatePaymentResponse add(RentalPaymentCreatedEvent createPaymentRequest) {
		checkBalanceEnough(createPaymentRequest.getBalance(),createPaymentRequest.getTotalPrice());
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		
		Payment result = paymentRepository.save(payment);
		CreatePaymentResponse createPaymentResponse = modelMapperService.forResponse().map(result, CreatePaymentResponse.class);
		return createPaymentResponse;
	}
	
	private void checkBalanceEnough(double balance, double totalPrice) {
		if (totalPrice>balance) {
			throw new BusinessException("BALANCE.IS.NOT.ENOUGH");
		}
	}

}
