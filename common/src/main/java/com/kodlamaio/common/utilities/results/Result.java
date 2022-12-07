package com.kodlamaio.common.utilities.results;

public class Result {

	private boolean success;
	private String messega;

	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String message) {
		this(success);
		this.messega = message;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public String getMessage() {
		return this.messega;
	}

}