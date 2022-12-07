package com.kodlamaio.paymentservice.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.rentalPayment.PayMoneyRequest;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {

	private PaymentService paymentService;

	@PostMapping()
	public DataResult<CreatePaymentResponse> add(@Valid @RequestBody PayMoneyRequest createPaymentRequest) {
		return paymentService.add(createPaymentRequest);
	}
}
