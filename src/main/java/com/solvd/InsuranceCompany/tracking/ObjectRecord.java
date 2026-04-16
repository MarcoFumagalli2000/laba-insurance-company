package com.solvd.InsuranceCompany.Tracking;

import com.solvd.InsuranceCompany.Exceptions.IncompleteRecord;
import com.solvd.InsuranceCompany.Interfaces.IBrakes;

public class ObjectRecord extends Records implements IBrakes {

	private boolean isStolen;
	private int totalBrakes;

	public ObjectRecord(String info, String details, ClientRequests request, boolean isStolen, int brokenObjectNumber)
		throws IncompleteRecord {
		super(info, details, request);
		if (request == null) {
			throw new IncompleteRecord("Object Records cannot be created without a valid ClientRequest.");
		}
		this.isStolen = isStolen;
		this.totalBrakes = brokenObjectNumber;
	}

	public boolean isStolen() {
		return isStolen;
	}

	public void setStolen(boolean stolen) {
		isStolen = stolen;
	}

	public int getBrokenObjectNumber() {
		return totalBrakes;
	}

	public void setBrokenObjectNumber(int brokenObjectNumber) {
		this.totalBrakes = brokenObjectNumber;
	}

	@Override
	public void recordNewBrake() {
		this.totalBrakes++;
	}

	@Override
	public int totalBrakes() {
		return this.totalBrakes;
	}
}
