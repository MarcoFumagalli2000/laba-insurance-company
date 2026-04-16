package com.solvd.InsuranceCompany.people;

import com.solvd.InsuranceCompany.items.InsurancedItem;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
	private final List<InsurancedItem> myItems = new ArrayList<>();
	public Client(String name, String lastName, String idNumber, String phoneNumber, String email) {
		super(name, lastName, idNumber, phoneNumber, email);
	}

	public void addAsset(InsurancedItem item) {
		this.myItems.add(item);
	}

	public List<InsurancedItem> getMyAssets() {
		return myItems;
	}

	@Override
	public String toString() {
		return this.getName() + " " + this.getLastName();
	}
}
