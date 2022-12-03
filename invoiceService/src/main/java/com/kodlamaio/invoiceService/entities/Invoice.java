package com.kodlamaio.invoiceService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="invoices")
public class Invoice {

	@Id
	@Column(name="id")
	private String id;
	
	private String rentalId;
	
	private String carName;
	
	private String carModel;
	
	private double totalPrice;
	
	
}
