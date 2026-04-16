package com.solvd.InsuranceCompany.Tools;

import com.solvd.InsuranceCompany.Interfaces.ICalculate;
import com.solvd.InsuranceCompany.Items.OtherObjects;
import com.solvd.InsuranceCompany.People.Client;
import com.solvd.InsuranceCompany.Tracking.ObjectRecord;

public class ObjectCalculator extends BaseCalculator<OtherObjects, ObjectRecord> implements ICalculate<OtherObjects> {

	private Client owner;
	private double repairPrice;

	public ObjectCalculator(
			Client owner,
			double repairPrice,
			OtherObjects object,
			ObjectRecord records
	) {
		super(object, records);
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

	public OtherObjects getObject() {
		return this.item;
	}

	public void setObject(OtherObjects object) {
		this.item = object;
	}

	public ObjectRecord getRecords() {
		return records;
	}

	public void setRecords(ObjectRecord records) {
		this.records = records;
	}

	@Override
	public double calculatePayout(OtherObjects item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}
		this.item = item;
		return calculatePayout();
	}

	@Override
	public double calculatePayout() {
		if (this.records.isStolen()) {
			return this.item.getValue();
		}
		else if (this.records.totalBrakes() > 2) {
			return this.repairPrice * 0.7;
		} else {
			return this.repairPrice * 0.9;
		}
	}


}
