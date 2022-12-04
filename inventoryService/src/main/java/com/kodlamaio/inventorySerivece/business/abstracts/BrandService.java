package com.kodlamaio.inventorySerivece.business.abstracts;

import java.util.List;

import com.kodlamaio.inventorySerivece.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventorySerivece.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventorySerivece.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventorySerivece.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventorySerivece.business.responses.update.UpdateBrandResponse;

public interface BrandService {


  CreateBrandResponse add(CreateBrandRequest createBrandRequest);
  UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);
  void delete (String id);
  List<GetAllBrandsResponse> getAll();
  GetBrandResponse getById(String id);
}
