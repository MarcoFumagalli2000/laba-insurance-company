package com.solvd.InsuranceCompany.Interfaces;

import com.solvd.InsuranceCompany.Items.InsurancedItem;

public interface ICalculate<T extends InsurancedItem> {
	double calculatePayout(T item);
}
