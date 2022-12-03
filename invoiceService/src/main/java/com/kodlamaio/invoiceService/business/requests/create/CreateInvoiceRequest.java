package com.kodlamaio.invoiceService.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInvoiceRequest {
	private String id;

	private String rentalId;

	private String carName;

	private String carModel;

	private double totalPrice;
}
