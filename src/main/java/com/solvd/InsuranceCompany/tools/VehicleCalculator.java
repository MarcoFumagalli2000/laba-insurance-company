package com.solvd.InsuranceCompany.Tools;

import com.solvd.InsuranceCompany.Interfaces.ICalculate;
import com.solvd.InsuranceCompany.Items.Vehicle;
import com.solvd.InsuranceCompany.People.Client;
import com.solvd.InsuranceCompany.Tracking.VehicleRecords;

public class VehicleCalculator extends BaseCalculator<Vehicle, VehicleRecords> implements ICalculate<Vehicle> {

	private Client owner;
	private double repairPrice;

	public VehicleCalculator(Client owner, double repairPrice, Vehicle item, Vehicle vehicle, VehicleRecords records) {
		super(item, records);
		this.owner = owner;
		this.repairPrice = repairPrice;
	}

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public double getRepairPrice() {
		return repairPrice;
	}

	public void setRepairPrice(double repairPrice) {
		this.repairPrice = repairPrice;
	}

	@Override
	public double calculatePayout(Vehicle item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}
		this.item = item;
		return calculatePayout();
	}

	@Override
	public double calculatePayout() {
		double base = this.getRepairPrice();
		if (this.records.crashNumbers() > 3) {
			return base * 0.7;
		}
		return base * 0.9;
	}

}
