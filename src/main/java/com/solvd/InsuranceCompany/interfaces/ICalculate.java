package com.solvd.InsuranceCompany.interfaces;

import com.solvd.InsuranceCompany.items.InsurancedItem;

public interface ICalculate<T extends InsurancedItem> {
	double calculatePayout(T item);
}
