package com.kodlamaio.inventorySerivece.business.requests.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBrandRequest {

	@NotNull
	@NotBlank
	@Size(min = 1 ,max = 22)
	private String name;
}
