package com.kodlamaio.inventorySerivece;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.kodlamaio.common.utilities.mapping.ModelMapperManager;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventorySerivece.dataAccess.mongoDb.CarMongoRepository;
import com.kodlamaio.inventorySerivece.entities.mongo.CarMongoDetail;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories(basePackageClasses = CarMongoRepository.class)
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CarMongoRepository.class))
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
 