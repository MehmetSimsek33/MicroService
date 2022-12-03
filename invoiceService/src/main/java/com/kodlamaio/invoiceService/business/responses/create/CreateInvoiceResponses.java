package com.kodlamaio.invoiceService.business.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInvoiceResponses {

	private String id;
	
	private String rentalId;
	
	private String carName;
	
	private String carModel;
	
	private double totalPrice;
}
