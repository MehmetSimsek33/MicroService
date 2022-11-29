package com.kodlamaio.inventorySerivece.business.concrete;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.commen.utilities.excaptions.BusinessException;
import com.kodlamaio.commen.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventorySerivece.business.abstracts.CarService;
import com.kodlamaio.inventorySerivece.business.abstracts.ModelService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventorySerivece.dataAccess.CarRepository;
import com.kodlamaio.inventorySerivece.entities.Brand;
import com.kodlamaio.inventorySerivece.entities.Car;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarManager implements CarService {

	private ModelMapperService modelMapperService;
	private CarRepository carRepository;
	private ModelService modelService;
	@Override
	public List<GetAllCarResponse> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarResponse.class))
				.collect(Collectors.toList());

		return response;
	}


	@Override
	public CreateCarResponse add(CreateCarRequest createCarRequest) {
	
		checkICarExistsModel(createCarRequest.getModelId());
		Car car=this.modelMapperService.forRequest().map(createCarRequest,Car.class);
		carRepository.save(car);
		CreateCarResponse carResponse=
				this.modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return carResponse;
	}
	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		checkIfCarExistsById(updateCarRequest.getId());
		checkICarExistsModel(updateCarRequest.getModelId());

		Car car=this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
		carRepository.save(car);
		UpdateCarResponse carResponse=this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return carResponse;
	}


	@Override
	public void delete(String id) {
		checkIfCarExistsById(id);
		this.carRepository.deleteById(id);
		
	}
	private void checkIfCarExistsById(String id) {
		Car currentCar = this.carRepository.findById(id).orElse(null);
				if (currentCar == null) { 
			throw new BusinessException("Car not.EXISTS");
		}
	}


	@Override
	public GetCarResponse getById(String id) {
		checkIfCarExistsById(id);
		Car car = this.carRepository.findById(id).get();
		GetCarResponse resCarResponse = this.modelMapperService.forResponse().map(car,
				GetCarResponse.class);
		return resCarResponse;
	}
	private void checkICarExistsModel(String id) {
		Object currentModel = this.modelService.getById(id);
				if (currentModel == null) { 
			throw new BusinessException("Model not.EXISTS");
		}
	}

	
 
}