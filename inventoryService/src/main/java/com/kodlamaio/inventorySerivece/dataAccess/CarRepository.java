package com.kodlamaio.inventorySerivece.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventorySerivece.entities.Car;

public interface CarRepository  extends JpaRepository<Car, String> {
	
	Car findByPlate(String id);
}
