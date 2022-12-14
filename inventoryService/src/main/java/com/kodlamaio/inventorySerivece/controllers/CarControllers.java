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
import com.kodlamaio.inventorySerivece.business.abstracts.CarService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateCarResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cars/")
@AllArgsConstructor
public class CarControllers {
	private CarService carService;

	@PostMapping
	public DataResult<CreateCarResponse> add(@RequestBody CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}

	@PutMapping
	public DataResult<UpdateCarResponse> update(@RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		this.carService.delete(id);
	}
	@GetMapping
	public DataResult<List<GetAllCarResponse>> getAll() {
		return this.carService.getAll();
	}
	@GetMapping("{id}")
	public void checkIfState(@PathVariable(name="id") String id) {
		 this.carService.checkIfState(id);
	}
}
