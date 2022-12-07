package com.kodlamaio.filterService.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDetailRequest {

	private String id;
	private String brandName;
	private String modelName;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private int state;
}
