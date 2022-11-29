package com.kodlamaio.inventorySerivece.business.abstracts;

import java.util.List;

import com.kodlamaio.inventorySerivece.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateModelResponse;

public interface ModelService {

	CreateModelResponse add(CreateModelRequest createModelRequest);

	UpdateModelResponse updateModelResponse(UpdateModelRequest updateModelRequest);

	void delete(String id);

	List<GetAllModelResponse> getAll();

	GetModelResponse getById(String id);
}
