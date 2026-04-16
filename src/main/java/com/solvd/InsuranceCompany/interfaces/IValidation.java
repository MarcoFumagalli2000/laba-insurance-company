package com.solvd.InsuranceCompany.interfaces;

import com.solvd.InsuranceCompany.tracking.ClientRequests;

@FunctionalInterface
public interface IValidation {
	boolean isValid(ClientRequests req);
}
