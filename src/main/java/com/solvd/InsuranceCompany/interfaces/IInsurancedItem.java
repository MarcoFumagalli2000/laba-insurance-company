package com.solvd.InsuranceCompany.items;

import com.solvd.InsuranceCompany.exceptions.InvalidValue;
import com.solvd.InsuranceCompany.interfaces.ITracker;
import com.solvd.InsuranceCompany.people.Client;
import java.util.concurrent.atomic.AtomicInteger;

public interface InsurancedItem extends ITracker {

	AtomicInteger ITEM_TRACKER = new AtomicInteger();

	static void validateValue(double value) {
		if (value <= 0) {
			throw new InvalidValue("Insurance value must be positive");
		}
	}

	static void registerItem(double value) {
		validateValue(value);
		ITEM_TRACKER.incrementAndGet();
	}

	Client owner();
	double value();

	@Override
	default int itemAmount() {
		return ITEM_TRACKER.get();
	}
}
