package com.kodlamaio.rentalService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
@FeignClient(value="client", url = "http://localhost:9010/")
public interface FeignClientUtill {	

	@RequestMapping(method =RequestMethod.GET,value ="stock/api/cars/{id}")
	@Headers(value = "Content-Type: application/json")
	void checkIfState(@PathVariable(name="id") String id);
}
