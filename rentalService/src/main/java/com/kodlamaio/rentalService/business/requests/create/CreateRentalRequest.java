package com.kodlamaio.rentalService.business.requests.create;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	private String carId;
	private int rentedForDays;
	private double dailyPrice;
	private String cardNo;
	private String cardHolder;
	private double balance;

}