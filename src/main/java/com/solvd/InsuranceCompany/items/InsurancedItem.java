package com.solvd.InsuranceCompany.items;

import com.solvd.InsuranceCompany.exceptions.InvalidValue;
import com.solvd.InsuranceCompany.interfaces.ITracker;
import com.solvd.InsuranceCompany.people.Client;

public abstract class InsurancedItem implements ITracker {

	private static int itemTracker = 0;
	private Client owner;
	private double value;

	public InsurancedItem(Client owner, double value) throws InvalidValue {
		if (value <= 0) {
			throw new InvalidValue("Insurance value must be positive");
		}
		this.owner = owner;
		this.value = value;
		itemTracker++;
	}

	public static int getItemTracker() {
		return itemTracker;
	}

	public static void setItemTracker(int itemTracker) {
		InsurancedItem.itemTracker = itemTracker;
	}

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int itemAmount() {
		return itemTracker;
	}
}
