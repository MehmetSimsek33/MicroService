package com.kodlamaio.inventorySerivece.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateCarResponse;

public interface CarService {

	DataResult<CreateCarResponse> add(CreateCarRequest createCarRequest);

	DataResult<UpdateCarResponse> update(UpdateCarRequest updateCarRequest);

	Result delete(String id);

	DataResult<List<GetAllCarResponse>> getAll();

	DataResult<GetCarResponse> getById(String id);
	
	void updateCarState(String carId, int state);
	
	void checkIfState(String id);

}
