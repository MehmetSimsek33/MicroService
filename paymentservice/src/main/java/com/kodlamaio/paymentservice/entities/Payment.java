package com.kodlamaio.paymentservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name="total_price")
	private double totalPrice;
	
	@Column(name="rental_id")
	private String rentalId;
	
	@Column(name="status")
	private int status;
	
	@Column(name = "card_no")
	private String cardNo;
	
	@Column(name = "card_holder")
	private String cardHolder;
	
	@Column(name = "balance")
	private double balance;
	
//	@ManyToOne
//	@JoinColumn(name = "payment_id")
//	private Payment payment;
	
}
