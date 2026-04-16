package com.solvd.InsuranceCompany.Exceptions;

public class UnauthorizedRequest extends RuntimeException {

	public UnauthorizedRequest(String message) {
		super(message);
	}
}
