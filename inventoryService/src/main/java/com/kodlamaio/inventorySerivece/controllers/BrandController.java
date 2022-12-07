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
import com.kodlamaio.inventorySerivece.business.abstracts.BrandService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands/")
@AllArgsConstructor
public class BrandController {

	private BrandService brandService;

	@PostMapping
	public DataResult<CreateBrandResponse> add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}

	@PutMapping
	public DataResult<UpdateBrandResponse> update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	@DeleteMapping("{id}")
	public Result delete(@PathVariable String id) {
		return this.brandService.delete(id);
	}
	@GetMapping
	public DataResult<List<GetAllBrandsResponse>> getAll(){
		return this.brandService.getAll();
	}
	
}
