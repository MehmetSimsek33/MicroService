package com.kodlamaio.filterService.business.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarDetailResponse {
	private String brandName;
	private String modelName;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private int state;
}
