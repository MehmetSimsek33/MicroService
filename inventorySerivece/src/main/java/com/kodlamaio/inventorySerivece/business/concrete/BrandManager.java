package com.kodlamaio.inventorySerivece.business.concrete;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.commen.utilities.excaptions.BusinessException;
import com.kodlamaio.commen.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventorySerivece.business.abstracts.BrandService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.inventorySerivece.dataAccess.BrandRepository;
import com.kodlamaio.inventorySerivece.entities.Brand;
import com.kodlamaio.inventorySerivece.entities.Car;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {

	private ModelMapperService modelMapperService;
	private BrandRepository brandRepository;

	@Override

	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands = this.brandRepository.findAll();

		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());

		this.brandRepository.save(brand);

		CreateBrandResponse createBrandResponse = this.modelMapperService.forResponse().map(brand,
				CreateBrandResponse.class);
		return createBrandResponse;
	}

	@Override
	public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistsById(updateBrandRequest.getId());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		UpdateBrandResponse responseBrandResponse = this.modelMapperService.forResponse().map(brand,
				UpdateBrandResponse.class);
		return responseBrandResponse;

	}
	@Override
	public GetBrandResponse getById(String id) {
		
		checkIfBrandExistsById(id);
		Brand brand = this.brandRepository.findById(id).get();
		GetBrandResponse responseBrandResponse = this.modelMapperService.forResponse().map(brand,
				GetBrandResponse.class);
		return responseBrandResponse;
		
	}
		
		

	private void checkIfBrandExistsByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}
	private void checkIfBrandExistsById(String id) {
		Brand currentBrand = this.brandRepository.findById(id).orElse(null);
				if (currentBrand == null) { 
			throw new BusinessException("Brand not.EXISTS");
		}
	}


	@Override
	public void delete(String id) {
		checkIfBrandExistsById(id);
		this.brandRepository.deleteById(id);
		
	}


	

}