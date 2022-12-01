package com.kodlamaio.inventorySerivece;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.kodlamaio.commen.utilities.mapping.ModelMapperManager;
import com.kodlamaio.commen.utilities.mapping.ModelMapperService;

@SpringBootApplication
@EnableDiscoveryClient
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
 