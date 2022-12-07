package com.kodlamaio.filterService.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.filter.brand.BrandUpdatedEvent;
import com.kodlamaio.common.event.filter.car.CarCreatedEvent;
import com.kodlamaio.common.event.filter.car.CarDeletedEvent;
import com.kodlamaio.common.event.filter.car.CarUpdatedEvent;
import com.kodlamaio.common.event.filter.model.ModelUpdatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterService.business.abstracts.FilterService;
import com.kodlamaio.filterService.business.requests.create.CreateCarDetailRequest;
import com.kodlamaio.filterService.business.responses.create.CreateCarDetailResponse;
import com.kodlamaio.filterService.business.responses.create.get.GetAllFiltersResponse;
import com.kodlamaio.filterService.business.responses.create.get.GetFilterResponse;
import com.kodlamaio.filterService.dataAccess.FilterRepository;
import com.kodlamaio.filterService.entities.Filter;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {

	private FilterRepository filterRepository;
	private ModelMapperService modelMapperService;

	@Override
	public CreateCarDetailResponse add(CreateCarDetailRequest createCarDetailRequest) {

		Filter carDetail = this.modelMapperService.forRequest().map(createCarDetailRequest, Filter.class);

		this.filterRepository.insert(carDetail);
		CreateCarDetailResponse response = this.modelMapperService.forResponse().map(carDetail,
				CreateCarDetailResponse.class);
		return response;
	}

	@Override
	public List<GetAllFiltersResponse> getAll() {
		List<Filter> filters = filterRepository.findAll();
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(filter -> this.modelMapperService.forRequest().map(filter, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByBrandName(String brandName) {
		List<Filter> filters = this.filterRepository.findByBrandName(brandName);
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(filter -> this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByModelName(String modelName) {
		List<Filter> filters = this.filterRepository.findByModelName(modelName);
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(filter -> this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public GetFilterResponse getByPlate(String plate) {
		Filter filter = this.filterRepository.findByPlate(plate);
		GetFilterResponse response = this.modelMapperService.forResponse().map(filter, GetFilterResponse.class);
		return response;
	}

	@Override
	public List<GetAllFiltersResponse> getByDailyPrice(double min, double max) {
		List<Filter> filters = this.filterRepository.findAll();
	
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllFiltersResponse.class))
				.filter(price-> price.getDailyPrice()<max && price.getDailyPrice()>min)
				.collect(Collectors.toList());
		
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByModelYear(int min, int max) {
		List<Filter> filters = this.filterRepository.findAll();
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllFiltersResponse.class))
				.filter(model-> model.getModelYear()<max && model.getModelYear()>min)
				.collect(Collectors.toList());
		
		return responses;
	}

	@Override
	public void addCar(CarCreatedEvent carCreatedEvent) {
		Filter filter = this.modelMapperService.forRequest().map(carCreatedEvent, Filter.class);
		this.filterRepository.save(filter);
	}

	@Override
	public void deleteCar(CarDeletedEvent carDeletedEvent) {
		Filter filter = this.filterRepository.findById(carDeletedEvent.getCarId()).get();
		this.filterRepository.delete(filter);

	}

	@Override
	public void updateCar(CarUpdatedEvent carUpdatedEvent) {
		Filter filter = this.modelMapperService.forRequest().map(carUpdatedEvent, Filter.class);
		this.filterRepository.save(filter);
	}

	@Override
	public void updateBrand(BrandUpdatedEvent brandUpdatedEvent) {
		List<Filter> filters = this.filterRepository.findByBrandName(brandUpdatedEvent.getBrandName());
		for (Filter filter : filters) {
			filter.setBrandName(brandUpdatedEvent.getBrandName());
			filter.setBrandId(brandUpdatedEvent.getBrandId());
			this.filterRepository.save(filter);
		}
	}

	@Override
	public void updateModel(ModelUpdatedEvent modelUpdatedEvent) {
		List<Filter> filters = this.filterRepository.findByModelName(modelUpdatedEvent.getModelName());
		for (Filter filter : filters) {
			filter.setBrandName(modelUpdatedEvent.getBrandName());
			filter.setBrandId(modelUpdatedEvent.getBrandId());
			filter.setModelName(modelUpdatedEvent.getModelName());
			filter.setModelId(modelUpdatedEvent.getModelId());
			this.filterRepository.save(filter);
		}

	}

}
