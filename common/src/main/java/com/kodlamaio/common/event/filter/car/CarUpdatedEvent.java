package com.kodlamaio.common.event.filter.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdatedEvent {
	private String id;
	private String brandName;
	private String modelName;
	private String plate;
	private double dailyPrice;
	private int modelYear;
	private int state;
}