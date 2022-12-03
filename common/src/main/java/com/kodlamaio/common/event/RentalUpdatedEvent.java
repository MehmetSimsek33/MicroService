package com.kodlamaio.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalUpdatedEvent {
	private String message;
	private String oldCarId;
	private String newCarId;
}