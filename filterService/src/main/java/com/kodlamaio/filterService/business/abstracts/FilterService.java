package com.kodlamaio.filterService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.event.filter.brand.BrandUpdatedEvent;
import com.kodlamaio.common.event.filter.car.CarCreatedEvent;
import com.kodlamaio.common.event.filter.car.CarDeletedEvent;
import com.kodlamaio.common.event.filter.car.CarUpdatedEvent;
import com.kodlamaio.common.event.filter.model.ModelUpdatedEvent;
import com.kodlamaio.filterService.business.requests.create.CreateCarDetailRequest;
import com.kodlamaio.filterService.business.responses.create.CreateCarDetailResponse;
import com.kodlamaio.filterService.business.responses.create.get.GetAllFiltersResponse;
import com.kodlamaio.filterService.business.responses.create.get.GetFilterResponse;

public interface FilterService {

	CreateCarDetailResponse add(CreateCarDetailRequest createCarDetailRequest);

	List<GetAllFiltersResponse> getAll();

	List<GetAllFiltersResponse> getByBrandName(String brandName);

	List<GetAllFiltersResponse> getByModelName(String modelName);

	GetFilterResponse getByPlate(String plate);

	List<GetAllFiltersResponse> getByDailyPrice(double min, double max);

	List<GetAllFiltersResponse> getByModelYear(int min, int max);

	void addCar(CarCreatedEvent carCreatedEvent);

	void deleteCar(CarDeletedEvent carDeletedEvent);

	void updateCar(CarUpdatedEvent carUpdatedEvent);

	void updateBrand(BrandUpdatedEvent brandUpdatedEvent);

	void updateModel(ModelUpdatedEvent modelUpdatedEvent);

}
