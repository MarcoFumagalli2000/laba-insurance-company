package com.solvd.InsuranceCompany.Tools;

import com.solvd.InsuranceCompany.Items.InsurancedItem;
import com.solvd.InsuranceCompany.Tracking.Records;

public abstract class BaseCalculator<T extends InsurancedItem, R extends Records> {

	protected T item;
	protected R records;

	public BaseCalculator(T item, R records) {
		this.item = item;
		this.records = records;
	}

	public abstract double calculatePayout();
}
