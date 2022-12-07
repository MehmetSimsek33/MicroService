package com.kodlamaio.inventorySerivece.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.inventorySerivece.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateBrandResponse;

public interface BrandService {


  DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest);
  DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest);
  Result delete (String id);
  DataResult<List<GetAllBrandsResponse>> getAll();
  DataResult<GetBrandResponse> getById(String id);
}
