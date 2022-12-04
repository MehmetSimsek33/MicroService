package com.kodlamaio.inventorySerivece.business.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetModelResponse {
	private String id;
	private String name;
	private String brandName;
}
