package com.kodlamaio.rentalService.api;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {
	private RentalService rentalService;

	@PostMapping
	public  DataResult<CreateRentalResponse> add(@Valid @RequestBody CreateRentalRequest createBrandRequest) {
		return rentalService.add(createBrandRequest);
	}
	
	@PutMapping
	public  DataResult<UpdateRentalResponse> update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
		return rentalService.update(updateRentalRequest);
	}
}