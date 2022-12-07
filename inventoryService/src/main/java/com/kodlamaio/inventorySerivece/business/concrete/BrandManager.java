package com.kodlamaio.inventorySerivece.business.concrete;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccesResult;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.inventorySerivece.business.abstracts.BrandService;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.inventorySerivece.dataAccess.BrandRepository;
import com.kodlamaio.inventorySerivece.entities.Brand;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {

	private ModelMapperService modelMapperService;
	private BrandRepository brandRepository;

	@Override

	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();

		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllBrandsResponse>>(response);
	}

	@Override
	public DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());

		this.brandRepository.save(brand);

		CreateBrandResponse createBrandResponse = this.modelMapperService.forResponse().map(brand,
				CreateBrandResponse.class);
		return new SuccessDataResult<CreateBrandResponse>(createBrandResponse) ;
	}

	@Override
	public DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistsById(updateBrandRequest.getId());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		UpdateBrandResponse responseBrandResponse = this.modelMapperService.forResponse().map(brand,
				UpdateBrandResponse.class);
		return new SuccessDataResult<UpdateBrandResponse>(responseBrandResponse) ;

	}

	@Override
	public  DataResult<GetBrandResponse> getById(String id) {

		checkIfBrandExistsById(id);
		Brand brand = this.brandRepository.findById(id).get();
		GetBrandResponse responseBrandResponse = this.modelMapperService.forResponse().map(brand,
				GetBrandResponse.class);
		return new SuccessDataResult<GetBrandResponse>(responseBrandResponse) ;

	}

	@Override
	public Result delete(String id) {
		checkIfBrandExistsById(id);
		this.brandRepository.deleteById(id);
		return new SuccesResult();
	}

	private void checkIfBrandExistsById(String id) {
		Brand currentBrand = this.brandRepository.findById(id).orElse(null);
		if (currentBrand == null) {
			throw new BusinessException("Brand not.EXISTS");
		}
	}

	private void checkIfBrandExistsByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}

}
