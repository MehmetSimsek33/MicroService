package com.kodlamaio.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalCreateInvoice {
	
	private String rentalId;
	
	private String carName;
	
	private String carModel;
	
	private double totalPrice;
}
