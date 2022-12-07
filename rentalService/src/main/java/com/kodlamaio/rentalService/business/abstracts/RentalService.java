package com.kodlamaio.rentalService.business.abstracts;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;

public interface RentalService {
	DataResult<CreateRentalResponse> add(CreateRentalRequest createRentalRequest);

	DataResult<UpdateRentalResponse> update(UpdateRentalRequest updateRentalRequest);
}