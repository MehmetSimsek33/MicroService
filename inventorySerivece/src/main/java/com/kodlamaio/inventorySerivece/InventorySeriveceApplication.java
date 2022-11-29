package com.kodlamaio.inventorySerivece;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kodlamaio.commen.utilities.mapping.ModelMapperManager;
import com.kodlamaio.commen.utilities.mapping.ModelMapperService;

@SpringBootApplication
public class InventorySeriveceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySeriveceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public ModelMapperService getModelMapperService(ModelMapper modelMapper) {
		return new ModelMapperManager(modelMapper);
	}
}
 