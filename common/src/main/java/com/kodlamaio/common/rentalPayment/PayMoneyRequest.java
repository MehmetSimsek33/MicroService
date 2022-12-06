package com.kodlamaio.common.rentalPayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayMoneyRequest {
	private String rentalId;
	private double totalPrice;
	private double balance;
	private String cardHolder;
	private String cardNo;
}
