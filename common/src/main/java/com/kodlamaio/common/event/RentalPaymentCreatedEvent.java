package com.kodlamaio.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalPaymentCreatedEvent {
	private String message;
	private String rentalId;
	private String id;
	private String cardNo;
	private String cardHolder;
	private double totalPrice;
	private double balance;
}
