package com.kodlamaio.inventorySerivece.entities.mongo;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class CarMongoDetail {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "brandId")
	private String brandId;

	@Column(name = "brandName")
	private String brandName;

	@Column(name = "modelId")
	private String modelId;

	@Column(name = "modelName")
	private String modelName;

	@Column(name = "dailyPrice")
	private double dailyPrice;

	@Column(name = "modelYear")
	private int modelYear;

	@Column(name = "plate")
	private String plate;

	@Column(name = "state")
	private int state;
}
