package com.kodlamaio.inventorySerivece.business.abstracts;

import java.util.List;

import com.kodlamaio.inventorySerivece.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateCarResponse;

public interface CarService {

	CreateCarResponse add(CreateCarRequest createCarRequest);

	UpdateCarResponse update(UpdateCarRequest updateCarRequest);

	void delete(String id);

	List<GetAllCarResponse> getAll();

	GetCarResponse getById(String id);

}