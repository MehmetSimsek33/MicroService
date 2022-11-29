package com.kodlamaio.inventorySerivece.business.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllModelResponse {
	private String id;
	private String name;
	private String brandName;
}