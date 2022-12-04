package com.kodlamaio.inventorySerivece.business.concrete;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventorySerivece.business.abstracts.BrandService;
import com.kodlamaio.inventorySerivece.business.abstracts.ModelService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetModelResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventorySerivece.dataAccess.ModelRepository;
import com.kodlamaio.inventorySerivece.entities.Model;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelMapperService modelMapperService;
	private ModelRepository modelRepository;
	private BrandService brandService;

	@Override
	public List<GetAllModelResponse> getAll() {
		List<Model> models = this.modelRepository.findAll();

		List<GetAllModelResponse> response = models.stream()
				.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public CreateModelResponse add(CreateModelRequest createModelRequest) {
		checkIfModelExistsBrand(createModelRequest.getBrandId());
		checkIfModelExistsByName(createModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		modelRepository.save(model);
		CreateModelResponse modelResponse = this.modelMapperService.forResponse().map(model, CreateModelResponse.class);
		return modelResponse;
	}

	@Override
	public UpdateModelResponse updateModelResponse(UpdateModelRequest updateModelRequest) {
		checkIfModelExistsById(updateModelRequest.getId());
		checkIfModelExistsBrand(updateModelRequest.getBrandId());
		checkIfModelExistsByName(updateModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
		modelRepository.save(model);
		UpdateModelResponse modelResponse = this.modelMapperService.forResponse().map(model, UpdateModelResponse.class);
		return modelResponse;
	}

	@Override
	public void delete(String id) {
		checkIfModelExistsById(id);
		this.modelRepository.deleteById(id);

	}

	@Override
	public GetModelResponse getById(String id) {
		checkIfModelExistsById(id);
		Model model = this.modelRepository.findById(id).get();
		GetModelResponse resCarResponse = this.modelMapperService.forResponse().map(model, GetModelResponse.class);
		return resCarResponse;
	}

	private void checkIfModelExistsById(String id) {
		Model currentModel = this.modelRepository.findById(id).orElse(null);
		if (currentModel == null) {
			throw new BusinessException("Model not.EXISTS");
		}
	}

	private void checkIfModelExistsBrand(String id) {
		Object currentBrand = this.brandService.getById(id);
		if (currentBrand == null) {
			throw new BusinessException("Brand not.EXISTS");
		}
	}

	private void checkIfModelExistsByName(String name) {
		Model currentModel = this.modelRepository.findByName(name);
		if (currentModel != null) {
			throw new BusinessException("MODEL.EXISTS");
		}
	}

}