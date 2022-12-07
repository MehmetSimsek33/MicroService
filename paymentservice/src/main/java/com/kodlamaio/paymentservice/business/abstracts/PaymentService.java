package com.kodlamaio.paymentservice.business.abstracts;

import com.kodlamaio.common.rentalPayment.PayMoneyRequest;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;

public interface PaymentService {

	DataResult<CreatePaymentResponse> add(PayMoneyRequest createPaymentRequest);
}
