package com.kodlamaio.rentalService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.common.rentalPayment.PayMoneyRequest;

import feign.Headers;

@FeignClient(value="paymentClien", url = "http://localhost:9010/")
public interface PaymentClient {	
	@RequestMapping(method =RequestMethod.POST,value ="payment/api/payments")
	@Headers(value = "Content-Type: application/json")
	Object add(@RequestBody PayMoneyRequest  payMoneyRequest);
}
