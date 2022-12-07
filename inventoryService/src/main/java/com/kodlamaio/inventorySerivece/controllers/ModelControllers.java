package com.kodlamaio.inventorySerivece.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.inventorySerivece.business.abstracts.ModelService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateModelResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/models/")
@AllArgsConstructor
public class ModelControllers {

	private ModelService modelService;
	
	@PostMapping
	public DataResult<CreateModelResponse> add(@RequestBody CreateModelRequest createModelRequest) {
		return this.modelService.add(createModelRequest);
	}
	
	@PutMapping
	public DataResult<UpdateModelResponse> updateModelResponse(@RequestBody UpdateModelRequest updateModelRequest) {
		return this.modelService.updateModelResponse(updateModelRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable String id) {
		return this.modelService.delete(id);
	}
	
	@GetMapping
	public DataResult<List<GetAllModelResponse>> getAll(){
		return this.modelService.getAll();
	}
}
