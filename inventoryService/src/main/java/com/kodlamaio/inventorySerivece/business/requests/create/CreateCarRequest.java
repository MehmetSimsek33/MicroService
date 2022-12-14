package com.kodlamaio.inventorySerivece.business.requests.create;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kodlamaio.inventorySerivece.entities.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {

	
	@NotBlank
	@Size(min = 1 ,max = 22)
	private String name;
	@NotNull
	@Min(1)
	private double dailyPrice;
	@NotNull
	@Min(2015)
	private int modelYear;
	@NotNull
	@NotBlank
	@Size(min = 1 ,max = 22)
	private String plate;
	@NotNull
	@Min(1)
	@Max(3)
	private int state;
	@NotNull
	@NotBlank
	private String modelId;
	
}
