package com.kodlamaio.inventorySerivece.business.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarResponse {

	private String id;
	private String name;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private int state;
	private String modelId;
}
