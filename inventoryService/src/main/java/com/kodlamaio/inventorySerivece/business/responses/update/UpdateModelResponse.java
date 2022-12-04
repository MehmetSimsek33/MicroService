package com.kodlamaio.inventorySerivece.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateModelResponse {
	private String id;
	private String name;
	private String brandName;
}
