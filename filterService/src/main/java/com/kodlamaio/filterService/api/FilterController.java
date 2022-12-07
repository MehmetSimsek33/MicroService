package com.kodlamaio.filterService.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.filterService.business.abstracts.FilterService;
import com.kodlamaio.filterService.business.requests.create.CreateCarDetailRequest;
import com.kodlamaio.filterService.business.responses.create.CreateCarDetailResponse;

import lombok.AllArgsConstructor;

@RequestMapping("api/cardetails")
@RestController
@AllArgsConstructor
public class FilterController {

	private FilterService filterService;
	
	@PostMapping()
	public CreateCarDetailResponse add(@RequestBody CreateCarDetailRequest carDetailRequest) {
		return this.filterService.add(carDetailRequest);
	}
}
