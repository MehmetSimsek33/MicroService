package com.kodlamaio.common.event.filter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelCreatedEvent {
	private String message;
	private String modelId;
	private String modelName;
	private String brandId;
	private String brandName;
}