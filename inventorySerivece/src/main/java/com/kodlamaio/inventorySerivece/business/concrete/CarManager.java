package com.kodlamaio.inventorySerivece.business.concrete;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
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
		checkIfCarExistsByPlate(createCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		carRepository.save(car);
		CreateCarResponse carResponse = this.modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return carResponse;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		checkIfCarExistsById(updateCarRequest.getId());
		checkICarExistsModel(updateCarRequest.getModelId());
		checkIfCarExistsByPlate(updateCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carRepository.save(car);
		UpdateCarResponse carResponse = this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return carResponse;
	}

	@Override
	public void delete(String id) {
		checkIfCarExistsById(id);
		this.carRepository.deleteById(id);
	}

	@Override
	public GetCarResponse getById(String id) {
		// checkIfCarExistsById(id);
		Car car = this.carRepository.findById(id).get();
		GetCarResponse resCarResponse = this.modelMapperService.forResponse().map(car, GetCarResponse.class);
		return resCarResponse;
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
