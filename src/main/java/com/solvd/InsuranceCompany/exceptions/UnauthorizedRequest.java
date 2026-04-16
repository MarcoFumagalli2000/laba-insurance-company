package com.solvd.InsuranceCompany.exceptions;

public class UnauthorizedRequest extends RuntimeException {

	public UnauthorizedRequest(String message) {
		super(message);
	}
}
