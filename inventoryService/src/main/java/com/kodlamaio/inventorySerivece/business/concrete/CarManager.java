package com.kodlamaio.inventorySerivece.business.concrete;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.filter.car.CarCreatedEvent;
import com.kodlamaio.common.event.filter.car.CarDeletedEvent;
import com.kodlamaio.common.event.filter.car.CarUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccesResult;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.inventorySerivece.business.abstracts.CarService;
import com.kodlamaio.inventorySerivece.business.abstracts.ModelService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetCarResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventorySerivece.dataAccess.CarRepository;
import com.kodlamaio.inventorySerivece.entities.Car;
import com.kodlamaio.inventorySerivece.kafka.InventoryProducer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarManager implements CarService {

	private ModelMapperService modelMapperService;
	private CarRepository carRepository;
	private ModelService modelService;
	private InventoryProducer inventoryProducer;

	@Override
	public DataResult<List<GetAllCarResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllCarResponse>>(response);
	}

	@Override
	public  DataResult<CreateCarResponse> add(CreateCarRequest createCarRequest) {

		checkICarExistsModel(createCarRequest.getModelId());
		checkIfCarExistsByPlate(createCarRequest.getPlate());

		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		Car result = carRepository.save(car);

		CreateCarResponse carResponse = this.modelMapperService.forResponse().map(result, CreateCarResponse.class);
		CarCreatedEvent carCreatedEvent = this.modelMapperService.forRequest().map(result, CarCreatedEvent.class);
		carCreatedEvent.setId(result.getId());
		carCreatedEvent.setModelId(result.getModel().getId());
		carCreatedEvent.setModelName(result.getModel().getName());
		carCreatedEvent.setBrandId(result.getModel().getBrand().getId());
		carCreatedEvent.setBrandName(result.getModel().getBrand().getName());
		this.inventoryProducer.sendMessage(carCreatedEvent);

		return new SuccessDataResult<CreateCarResponse>(carResponse);
	}

	@Override
	public  DataResult<UpdateCarResponse> update(UpdateCarRequest updateCarRequest) {
		checkIfCarExistsById(updateCarRequest.getId());
		checkICarExistsModel(updateCarRequest.getModelId());
		// checkIfCarExistsByPlate(updateCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		Car result=carRepository.save(car);
		CarUpdatedEvent carUpdatedEvent = this.modelMapperService.forRequest().map(result, CarUpdatedEvent.class);
		carUpdatedEvent.setId(result.getId());
		carUpdatedEvent.setModelName(result.getModel().getName());
		carUpdatedEvent.setBrandName(result.getModel().getBrand().getName());
		this.inventoryProducer.sendMessage(carUpdatedEvent);
		UpdateCarResponse carResponse = this.modelMapperService.forResponse().map(result, UpdateCarResponse.class);
		return new SuccessDataResult<UpdateCarResponse>(carResponse);
	}
 
	@Override
	public Result delete(String id) {
		checkIfCarExistsById(id);
		this.carRepository.deleteById(id);
		CarDeletedEvent carDeletedEvent=new CarDeletedEvent();
		carDeletedEvent.setCarId(id);		
		this.inventoryProducer.sendMessage(carDeletedEvent);
		return new SuccesResult();
	}

	@Override
	public DataResult<GetCarResponse> getById(String id) {
		// checkIfCarExistsById(id);
		Car car = this.carRepository.findById(id).get();
		GetCarResponse resCarResponse = this.modelMapperService.forResponse().map(car, GetCarResponse.class);
		return new SuccessDataResult<GetCarResponse>(resCarResponse);
	}

	@Override
	public void updateCarState(String carId, int state) {
		Car car = carRepository.findById(carId).get();
		System.out.println(car.getDailyPrice());
		car.setState(state);
		carRepository.save(car);
		
	}

	@Override
	public void checkIfState(String id) {
		Car result = carRepository.findById(id).orElse(null);
		if (result.getState() != 1 || result == null) {
			throw new BusinessException("CAR.NO.AVAILABLE");
		}
	
	}

	private void checkICarExistsModel(String id) {
		Object currentModel = this.modelService.getById(id);
		if (currentModel == null) {
			throw new BusinessException("Model not.EXISTS");
		}
	}

	private void checkIfCarExistsById(String id) {
		Car currentCar = this.carRepository.findById(id).orElse(null);
		if (currentCar == null) {
			throw new BusinessException("Car not.EXISTS");
		}
	}

	private void checkIfCarExistsByPlate(String plate) {
		var result = carRepository.findByPlate(plate);
		if (result != null) {
			throw new BusinessException("CAR.EXISTS");
		}
	}

}
