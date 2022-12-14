package com.kodlamaio.inventorySerivece.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventorySerivece.entities.Brand;
import com.kodlamaio.inventorySerivece.entities.Model;

public interface BrandRepository  extends JpaRepository<Brand, String> {

	Brand findByName(String name);
}

