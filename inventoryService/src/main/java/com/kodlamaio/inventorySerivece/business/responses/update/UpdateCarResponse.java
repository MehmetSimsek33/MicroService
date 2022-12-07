package com.kodlamaio.inventorySerivece.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarResponse {
	private String id;
	private double dailyPrice;
	private int modelYear;
	private String brandName;
	private String modelName;
}
