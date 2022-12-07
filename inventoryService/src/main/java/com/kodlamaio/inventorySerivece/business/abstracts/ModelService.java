package com.kodlamaio.inventorySerivece.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateModelResponse;

public interface ModelService {

	DataResult<CreateModelResponse> add(CreateModelRequest createModelRequest);

	DataResult<UpdateModelResponse> updateModelResponse(UpdateModelRequest updateModelRequest);

	Result delete(String id);

	DataResult<List<GetAllModelResponse>> getAll();

	DataResult<GetModelResponse> getById(String id);
}
