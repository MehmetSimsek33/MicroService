package com.kodlamaio.inventorySerivece.business.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCarResponse {
	private String id;
	private String name;
	private double dailyPrice;
	private int modelYear;
	private String brandName;
	private String modelName;
	private int state;

	}
