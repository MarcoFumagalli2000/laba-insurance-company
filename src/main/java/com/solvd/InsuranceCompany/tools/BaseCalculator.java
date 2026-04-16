package com.solvd.InsuranceCompany.tools;

import com.solvd.InsuranceCompany.interfaces.ITax;
import com.solvd.InsuranceCompany.items.InsurancedItem;
import com.solvd.InsuranceCompany.tracking.Records;

public abstract class BaseCalculator<T extends InsurancedItem, R extends Records> {

	protected static final double NATIONAL_TAX_RATE = 0.21;
	protected static final ITax TAX = baseValue -> baseValue * (1 - NATIONAL_TAX_RATE);
	protected T item;
	protected R records;

	public BaseCalculator(T item, R records) {
		this.item = item;
		this.records = records;
	}

	protected double applyTax(double baseValue) {
		return TAX.applyTax(baseValue);
	}

	public abstract double calculatePayout();
}
